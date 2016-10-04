package io.v.jenkins.plugins.vanadium_test_results_analyzer;

import java.sql.Timestamp;
import java.util.Collection;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import hudson.matrix.MatrixRun;
import hudson.tasks.junit.CaseResult;
import hudson.tasks.test.TestResult;

public class MongoDbTestResultsSender extends ResultsSender {

    public MongoDbTestResultsSender() {

    }

    @Override
    public void run() {
        try {
            MongoClient mongoClient = new MongoClient(ip, 27017);
            DB db = mongoClient.getDB(dbName);
            char a[] = password.toCharArray();
            boolean auth = db.authenticate(userName, a);
            DBCollection coll = db.createCollection("mycol", null);
            DBCollection colll = db.getCollection("mycol");
            String packageName = packageResult.getDisplayName();
            String className = classResult.getDisplayName();
            Collection<? extends TestResult> testCases = classResult.getChildren();
            for (TestResult testCase : testCases) {
                CaseResult caseResult = (CaseResult) testCase;
                String url = String.format("%stestReport/%s/%s/%s/", build.getUrl(), packageResult.getSafeName(),
                        classResult.getSafeName(), caseResult.getSafeName());
                String result = "PASSED";
                if (caseResult.isFailed()) {
                    result = "FAILED";
                } else if (caseResult.isSkipped()) {
                    result = "SKIPPED";
                }
                String subStringLabel = null;
                if (build instanceof MatrixRun) {
                    subStringLabel = build.getParent().getName();
                } else {
                    subStringLabel = null; // null for root build.
                }
                long curMs = System.currentTimeMillis();
                BasicDBObject doc = new BasicDBObject("jenkins_project", build.getRootBuild().getProject().getName())
                        .append("build_number", build.getNumber()).append("sub_build_labels", subStringLabel)
                        .append("test_package", packageName).append("test_class", className)
                        .append("test_case", caseResult.getDisplayName())
                        .append("test_full_name", caseResult.getFullDisplayName())
                        .append("start_time", new Timestamp(build.getStartTimeInMillis()))
                        .append("duration", caseResult.getDuration()).append("result", result).append("url", url)
                        .append("update_time", curMs);
                coll.insert(doc);
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

}
