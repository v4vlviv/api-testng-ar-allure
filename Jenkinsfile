node {
    stage ('SCM checkout'){
       	git 'https://github.com/v4vlviv/api-testng-ar-allure'
    }
    stage('Build') {
        sh './mvnw -Dmaven.test.failure.ignore=true clean verify site'
    }
    stage('Report') {
        publishHTML([reportName  : 'Allure Report', reportDir: 'target/site/allure-maven-plugin', reportFiles: 'index.html',
                   reportTitles: '', allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false])
    }
}