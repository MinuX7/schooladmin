# Setup database
## Install database
## Configure postgresql
- Location of config files should be in `/var/lib/pgsql/data`
- `postgresql.conf` configures database and `pg_hba.conf` configures access
## Setup users and database
```
 sudo -u postgres psql
 \du
 create user cosmind with password 'change_me' LOGIN CREATEDB;
 psql -U cosmind -h localhost -p 5432 -d postgres
 \l
 create database schooldb;
```

# Setup Docker
## Install docker
``` 
sudo dnf -y install dnf-plugins-core
sudo dnf-3 config-manager --add-repo https://download.docker.com/linux/fedora/docker-ce.repo 
sudo dnf install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
sudo systemctl enable --now docker
sudo usermod -aG docker cosmind
```

# Setup Kubernetes
## Install minikube
Install minikube
```
curl -LO https://github.com/kubernetes/minikube/releases/latest/download/minikube-linux-amd64
sudo install minikube-linux-amd64 /usr/local/bin/minikube && rm minikube-linux-amd64
```
Install Kubectl
```
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"


--Download and validate checksum
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl.sha256"
echo "$(cat kubectl.sha256)  kubectl" | sha256sum --check

sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```

# Setup GitHub
## Generate new Ssh Key
``` 
ssh-keygen -t ed25519 -C "cosmin.dumitrache7@gmail.com"
eval "$(ssh-agent -s)"
ssh-add ~/.ssh/id_ed25519
```
- Add public key to GitHub account: Settings -> SSH and GPG keys -> New SSH Key 

