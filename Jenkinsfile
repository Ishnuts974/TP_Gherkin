pipeline {
    agent any
    //triggers {
        //cron(15 12 * 2 1-5)
    //}

    stages {

        stage('Checkout') {
            steps {
                // Si ton projet n'est PAS sur Git, on saute cette étape
                echo 'Projet local - pas de checkout Git'
            }
        }

        stage('Build & Test') {
            steps {
                echo 'Execution des tests Cucumber via Maven...'
                    bat 'mvn clean test'

            }
        }
        stage('Export Json result to Xray'){
            steps {
            bat '''
                echo 'Exporting json file to Xray'
                curl -H "Content-Type: application/json" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnQiOiJiNmNhZGQwNS1lMzQxLTNmMTctYjU1Zi00OTM0MTI4MWQ4MmEiLCJhY2NvdW50SWQiOiI3MTIwMjA6OWYwYzgwZGQtY2I4ZC00NTAwLTk4NzItYTQ5MmEzOWU3MTRkIiwiaXNYZWEiOmZhbHNlLCJpYXQiOjE3NzAyODI3OTQsImV4cCI6MTc3MDM2OTE5NCwiYXVkIjoiQzRCRTk4MUExNUMzNEU4OEI5NDVDMTY3RDNGNTA5MDYiLCJpc3MiOiJjb20ueHBhbmRpdC5wbHVnaW5zLnhyYXkiLCJzdWIiOiJDNEJFOTgxQTE1QzM0RTg4Qjk0NUMxNjdEM0Y1MDkwNiJ9.n572zOHkJQv_pth9fQrz8lVgcYPEm-ZsWOWUjRx8tZw" -X POST --data@"target/cucumber.json" https://xray.cloud.getxray.app/api/v1/import/execution/cucumber
            '''

            }

        }
    }

    post {
        success {
            echo 'Tests exécutés avec succès 🎉'
        }

        failure {
            echo 'Des tests ont échoué ❌'
        }
    }

}