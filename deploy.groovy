node {
    // Credentials for Docker Hub and GKE (configure in Jenkins)
    withCredentials([
    usernamePassword(credentialsId: 'docker-hub-credentials', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME'),
    kubeconfigFile(credentialsId: 'gke-credentials', variable: 'KUBECONFIG')
  ]) {
        // Clone application repository
        git url: 'https://github.com/your-username/flask-app.git'

        // Deploy to GKE
        sh 'kubectl apply -f deployment.yaml'
        sh 'kubectl apply -f service.yaml'
  }
}
