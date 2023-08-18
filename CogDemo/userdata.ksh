#!/bin/bash 

# INstalling the Software 
echo "Update the system"
sudo yum update -y 
echo "Installing the Docker"
sudo yum install -y docker 
sudo service docker start 
sudo usermod -a -G docker ec2-user
echo "---------------------------------"
echo "Docker Installation Completed"

sudo curl -L https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m) -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

echo "---------------------------------"
echo "Docker-compose Installation Completed"
echo "---------------------------------"
echo "INstalling the Java 17 "
sudo dnf install java-17-amazon-corretto-devel -y 
echo "---------------------------------"

sudo wget http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo
sudo sed -i s/\$releasever/6/g /etc/yum.repos.d/epel-apache-maven.repo
sudo yum install -y apache-maven
mvn –version

echo "---------------------------------"
echo "Maven Installation Completed"
echo "---------------------------------"




sudo yum install git -y 

mkdir -p ~/projects
cd ~/projects



git clone -b kafka_conenct https://github.com/esak21/LearnBackendDevelopment.git

echo "---------------------------------"
echo "GIT Installation Completed"
echo "---------------------------------"




cd ~/projects/LearnBackendDevelopment/CogDemo/

sudo docker-compose up -d 



echo "---------------------------------"
echo "Docker compose Up completed
echo "---------------------------------"
