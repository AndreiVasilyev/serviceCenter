package by.epam.jwdsc.entity;

import java.util.List;

public class UserBuilders {

    public static ClientBuilder newClient() {
        return new ClientBuilder();
    }

    public static EmployeeBuilder newEmployee() {
        return new EmployeeBuilder();
    }

    public static class EmployeeBuilder extends AbstractUserBuilder<EmployeeBuilder, Employee> {

        private String login;
        private String password;

        public EmployeeBuilder login(String login) {
            this.login = login;
            return this;
        }

        public EmployeeBuilder password(String password) {
            this.password = password;
            return this;
        }

        @Override
        protected Employee innerBuild() {
            return new Employee(this.id, this.firstName, this.secondName, this.patronymic, this.address,
                    this.phones, this.email, this.userRole, this.login, this.password);
        }
    }

    public static class ClientBuilder extends AbstractUserBuilder<ClientBuilder, Client> {

        private int discount;

        public ClientBuilder discount(int discount) {
            this.discount = discount;
            return this;
        }

        @Override
        protected Client innerBuild() {
            return new Client(this.id, this.firstName, this.secondName, this.patronymic, this.address,
                    this.phones, this.email, this.userRole, this.discount);
        }
    }

    abstract static class AbstractUserBuilder<S extends UserBuilder, T extends AbstractUser> implements UserBuilder<S, T> {
        long id;
        String firstName;
        String secondName;
        String patronymic;
        Address address;
        List<String> phones;
        String email;
        UserRole userRole;

        @Override
        public S id(long id) {
            this.id = id;
            return self();
        }

        @Override
        public S firstName(String firstName) {
            this.firstName = firstName;
            return self();
        }

        @Override
        public S secondName(String secondName) {
            this.secondName = secondName;
            return self();
        }

        @Override
        public S patronymic(String patronymic) {
            this.patronymic = patronymic;
            return self();
        }

        @Override
        public S address(Address address) {
            this.address = address;
            return self();
        }

        @Override
        public S phones(List<String> phones) {
            this.phones = phones;
            return self();
        }

        @Override
        public S email(String email) {
            this.email = email;
            return self();
        }

        @Override
        public S userRole(UserRole userRole) {
            this.userRole = userRole;
            return self();
        }

        @Override
        public T build() {
            return innerBuild();
        }

        protected abstract T innerBuild();

        S self() {
            return (S) this;
        }
    }
}
