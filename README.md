# mywebapp
# Steps used to execute the build, push the image to Docker Hub, and deploy the application in a GKE cluster

1. Reserved a VM instance and single-node k8s cluster in Google Cloud.
2. Used the VM instance as a Jenkins server to build, push, and deploy the application.
3. Created a service account to access the k8s cluster from the Jenkins server.
4. Created a JSON private key and downloaded it to your desktop.
5. Created a firewall rule to allow traffic on port 8080 to access the Jenkins server on a web browser.
6. Pulled the GitHub repository "https://github.com/praveenvissapragada/mywebapp.git" on the VM instance.
7. Ran the shell script "jenkins_install.sh" present in the "jenkins" directory. This will install all the dependencies on the VM instance.
8. Logged in to the VM instance http://<External IP of the instance>:8080. Followed the instructions and deployed all the basic plugins until reaching the Jenkins home page.
9. Followed the path to add Docker Hub and Google Cloud service account details "Manage Jenkins > Manage Credentials > Global credentials (unrestricted) > Add Credentials"
   (i) Added Docker Hub credentials as Username and Password type. Credential ID should be "docker-credentials-id".
   (ii) Added Google Cloud service account credentials as Secret text, uploaded the JSON which was downloaded earlier while creating the service account. Credential ID should be "gke-credentials-id".
10. On the Jenkins dashboard page, clicked on "New Item", gave the name of the pipeline job, and selected "Pipeline" and clicked "OK".
11. Copied the contents of the "Jenkinsfile" present in the "jenkins" folder to "pipeline syntax" and saved the job.
12. Ran the job by clicking on the "Build now" option present in the left side panel.
13. Went back to Google console and connected to the k8s cluster using Cloud Shell.
14. Connected to the k8s cluster by executing the following command "gcloud container clusters get-credentials cluster-1 --zone us-central1-c --project deel-hometask".
15. Ran the "kubectl get services" command to get the "External IP address" of the "web-app-service" service. In the below example, the IP is "35.232.134.91"
    ```sh
    praveen_vissapragada@cloudshell:~$ kubectl get services
    NAME              TYPE           CLUSTER-IP   EXTERNAL-IP     PORT(S)        AGE
    kubernetes        ClusterIP      10.8.0.1     <none>          443/TCP        3h51m
    redis             ClusterIP      10.8.7.102   <none>          6379/TCP       34m
    web-app-service   LoadBalancer   10.8.6.152   35.232.134.91   80:30565/TCP   34m
    ```
16. Opened a web browser and went to "http://<external IP of the service>", to access the web application.