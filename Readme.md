
### Connect to local database
**!Note** Don't forget to check postgresql.conf `listen_addresses='*''` 
Also pg_hba.conf should allow user to connect from any host.

 `url: jdbc:postgresql://host.docker.internal:5432/schooldb`

### Run docker container. 

 `docker run --add-host host.docker.internal:host-gateway -it -p 8080:8080 schooladmin`

This allows connecting to localhost database.
 
### Running with docker compose.
School admin depends on externalschool and (optionally, if using docker database) by 
postgres_db) To start the stack execute: 
 
`docker-compose -p school -f school-docker-compose.yaml up --build`

To stop the stack execute: 

`docker-compose -f school-docker-compose.yaml -p school down`


### Kubernets

To use a local image build it inside minikube docker. Run: `eval $(minikube docker-env)`
Should be run in each terminal.

**!Note** DNS did not work for external school service. Need to restart dns service:
`kubectl -n kube-system rollout restart deployment coredns`

#### Use AWS as container registry. 

For detail configuration check this link [ Check this link](https://medium.com/@danieltse/pull-the-docker-image-from-aws-ecr-in-kubernetes-dc7280d74904)
 * Add ecr:* permissions to aws user. (..PolicyForECRAccess)
 * `aws ecr get-login-password --region region` to get auth token
 * Run `kubectl create secret docker-registry aws-ecr-secret --docker-server=<your-registry-server> --docker-username=<your-name> --docker-password=<your-pword> --docker-email=<your-email>`
      * <your-registry-server> would be aws_account_id.dkr.ecr.region.amazonaws.com.
      * <your-name> would be AWS.
      *  <your-pword> would be the login password from the AWS ECR command above.
      *  <your-email> email of your aws account.
  * Check generated secret: `kubectl get secret aws-ecr-secret --output=yaml` 
  * Define secret name under **imagePullSecrets** in deployment:

    
       imagePullSecrets:
         - name: regcred

###
test