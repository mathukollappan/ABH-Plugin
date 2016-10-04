package io.v.jenkins.plugins.vanadium_test_results_analyzer;

import hudson.model.AbstractBuild;
import hudson.tasks.test.TabulatedResult;
import hudson.tasks.test.TestResult;
import io.v.jenkins.plugins.vanadium_test_results_analyzer.MySqlTestResultsSender.TestResultsSenderEventHandler;

public abstract class ResultsSender implements Runnable {
    
    protected String ip;
    protected String dbName;
    protected String userName;
    protected String password;
    protected TestResult packageResult;
    protected TabulatedResult classResult;
    protected AbstractBuild<?, ?> build;
    protected String errMsg = "";
    protected TestResultsSenderEventHandler eventHandler;
    protected int port;
    
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getDbName() {
        return dbName;
    }
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public TestResult getPackageResult() {
        return packageResult;
    }
    public void setPackageResult(TestResult packageResult) {
        this.packageResult = packageResult;
    }
    public TabulatedResult getClassResult() {
        return classResult;
    }
    public void setClassResult(TabulatedResult classResult) {
        this.classResult = classResult;
    }
    public AbstractBuild<?, ?> getBuild() {
        return build;
    }
    public void setBuild(AbstractBuild<?, ?> build) {
        this.build = build;
    }
    public String getErrMsg() {
        return errMsg;
    }
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
    public TestResultsSenderEventHandler getEventHandler() {
        return eventHandler;
    }
    public void setEventHandler(TestResultsSenderEventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }
    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }

}
