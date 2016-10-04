package io.v.jenkins.plugins.vanadium_test_results_analyzer;

public class ResultSenderFactory {
    
    private static ResultsSender mongoDbResultSender = null;
    private static ResultsSender mySQLResultSender = null;
    public static ResultsSender getResultSender(String db)
    {
        if("mongo".equals(db))
        {
            if(mongoDbResultSender == null)
            {
                mongoDbResultSender = new MongoDbTestResultsSender();
            }
            return mongoDbResultSender;
        }
        else if("mysql".equals(db))
        {
            if(mySQLResultSender == null)
            {
                mySQLResultSender = new MySqlTestResultsSender();
            }
            return mySQLResultSender;
            
        }
        return null;
    }

}
