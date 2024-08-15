package httpserver.config;

public class ConfigurationManager {
    private static ConfigurationManager myConfigurationManager;
    private static ConfigurationManager myCurrentConfiguration;

    private ConfigurationManager() {
    }
    public static ConfigurationManager getInstance(){
        if(myConfigurationManager == null)
            myConfigurationManager = new ConfigurationManager();
        return myConfigurationManager;
    }

    // use to load configuration by filepath provided
    public void loadConfiguration(String filepath){

    }
    public void getCurrentConfiguration(){

    }


}
