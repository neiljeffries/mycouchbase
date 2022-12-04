
@echo off

rem LOCAL PATH CONFIG
set local_git_repo_path=C:\DeveloperApps\github
set test_skip=true
set active_profile=prod
set sonar_token=sqp_620de471383ea0a6bce3990e9b94c458fd70f534
set sonar_url=http://localhost:9000
set sonar_project=NEILSSPRINGBOOTAPP


rem START TOMCAT SERVER
start "Tomcat Server" cmd /K "cd %local_git_repo_path%\neils-spring-boot-app & mvn clean verify sonar:sonar"
@REM start "Tomcat Server" cmd /K "cd %local_git_repo_path%\neils-spring-boot-app & mvn clean verify sonar:sonar -Dsonar.login=%sonar_token%"
@REM start "Tomcat Server" cmd /K "cd %local_git_repo_path%\neils-spring-boot-app & mvn clean verify sonar:sonar -Dsonar.projectKey=%sonar_project% -Dsonar.host.url=%sonar_url% -Dsonar.login=%sonar_token%"
