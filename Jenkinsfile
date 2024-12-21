pipeline {
  agent any

  environment {
    SONARQUBE_URL = 'http://sonarqube:9000'
    SONARQUBE_TOKEN = 'squ_87824bb1cc87aac66f9ae2f6d4633b9d53405797'
    SONAR_PROJECT_KEY = "maska_hunters_league"
  }
  stages {
    stage('Build') {
      steps {
        echo 'Building the project...'
        sh 'mvn clean install -X'
      }
    }
    stage('SonarQube Scan') {
      steps {
        echo 'Running SonarQube analysis...'
        withSonarQubeEnv('sonarqub') {
          sh '''
            mvn sonar:sonar \
            -Dsonar.host.url=${SONARQUBE_URL} \
            -Dsonar.projectKey=$SONAR_PROJECT_KEY \
            -Dsonar.login=${SONARQUBE_TOKEN}
          '''
        }
      }
    }
    stage('Quality Gate Check') {
      steps {
        script {
          def qualityGate = sh(
            script: """
              curl -s -u "$SONARQUBE_TOKEN:" \
              "$SONARQUBE_URL/api/qualitygates/project_status?projectKey=$SONAR_PROJECT_KEY" \
              | jq -r '.projectStatus.status'
            """,
            returnStdout: true
          ).trim()
          if (qualityGate != "OK") {
            error "Quality Gate failed! Stopping the build."
          }
        }
      }
    }
    stage('Build Docker Image') {
      steps {
        echo "Building Docker Image..."
        sh 'docker build -t springboot-app .'
      }
    }
    stage('Deploy Docker Container') {
      steps {
        sh """
          docker stop springboot-app-container || true
          docker rm springboot-app-container || true
          docker run -d -p 8080:8080 --name springboot-app-container springboot-app
        """
      }
    }
  }
  post {
    success {
      echo 'Pipeline completed successfully! 🎉'
    }
    failure {
      echo 'Pipeline failed. Check the logs for details. 🚨'
    }
  }
}
