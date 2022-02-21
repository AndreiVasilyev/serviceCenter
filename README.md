# ServiceCenter

***

## ***Overview***

The web-project is designed to support the activities of a service center for the repair of consumer electronics and
household appliances. The system realizes the possibility of automating business processes within the service center,
and also provides access to customers to obtain all the necessary information on the activities of the enterprise and
the status of specific orders. Any unauthorized user gets the Guest role. Clients who are authorized with email
confirmation receive the Client role. Employees, after registration and authentication, have access to the appropriate
roles - Manager, Engineer, Administrator. Implemented internationalization for three languages - RU, EN, BL.
***

## ***Roles***

### **Guest**

* can use all the information pages of the site
* can go through the authorization procedure with email confirmation to obtain the role of Client
* can complete the registration process for the role of Manager or Engineer

### **Client**

* can use all the information pages of the site
* can view information about the current status of the order or previous orders by the order number from the receipt
* can reset the Client's status by logging out
* register for the status of Manager or Engineer

### **Manager**

* can place and save a new order
* can add a new device type during the checkout process
* can add a new device manufacturer during the checkout process
* can view information about any order
* can search for an order by any parameter using sorting and pagination when displayed
* can transfer an order from the CLOSED state to the ISSUED state
* \- can edit catalog of devices and producers
* \- can set\change prices for devices
* \- edit your profile
* \- change password or login
* can logout

### **Engineer**

* can view information about any order
* can search for an order by any parameter using sorting and pagination when displayed
* can take an order into work and transfer it to the status IN_PROGRESS
* can add/remove replaced parts from the order
* can complete the order and transfer it to the CLOSED status
* \- can edit catalog of spare parts
* \- edit your profile
* \- change password or login
* can logout

### **Admin**

* can place and save a new order
* can add a new device type during the checkout process
* can add a new device manufacturer during the checkout process
* can view information about any order
* can search for an order by any parameter using sorting and pagination when displayed
* can take an order into work and transfer it to the status IN_PROGRESS
* can add/remove replaced parts from the order
* can complete the order and transfer it to the CLOSED status
* can transfer an order from the CLOSED state to the ISSUED state
* can remove order
* \- can register employee
* \- can edit employee profiles
* \- edit your profile
* \- change password or login
* can logout

***

## ***Database Schema***

![service_сenter](https://user-images.githubusercontent.com/49836555/147941621-2943abc4-8735-4b05-abc5-537dcb44d2ec.png)
