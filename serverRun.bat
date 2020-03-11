@rem install jdk-8
@rem install maven
@rem add in %M2_HOME%\conf\settings.xml (if you are in IUT of Laval)
@rem    <proxy>
@rem      <id>optional</id>
@rem      <active>true</active>
@rem      <host>proxy.univ-lemans.fr</host>
@rem      <port>3128</port>
@rem   </proxy> 
mvn tomcat7:run
