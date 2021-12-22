package by.epam.jwdsc.servicecenter.entity;

import java.util.List;

public class UserBuilders {

    public static ClientBuilder newClient() {
        return new ClientBuilder();
    }

    public static EmployeeBuilder newEmployee() {
        return new EmployeeBuilder();
    }

    static class EmployeeBuilder extends AbstractUserBuilder<EmployeeBuilder, Employee> {

        private String login;
        private String password;
        private EmployeeRole role;

        public EmployeeBuilder login(String login) {
            this.login = login;
            return this;
        }

        public EmployeeBuilder password(String password) {
            this.password = password;
            return this;
        }

        public EmployeeBuilder role(EmployeeRole role) {
            this.role = role;
            return this;
        }

        @Override
        protected Employee innerBuild() {
            return new Employee(this.id, this.firstName, this.secondName, this.patronymic, this.address,
                    this.phones, this.email, this.login, this.password, this.role);
        }
    }

    static class ClientBuilder extends AbstractUserBuilder<ClientBuilder, Client> {

        private int discount;

        public ClientBuilder discount(int discount) {
            this.discount = discount;
            return this;
        }

        @Override
        protected Client innerBuild() {
            return new Client(this.id, this.firstName, this.secondName, this.patronymic, this.address,
                    this.phones, this.email, this.discount);
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

        @Override
        public S id(long id) {
            return self();
        }

        @Override
        public S firstName(String firstName) {
            return self();
        }

        @Override
        public S secondName(String secondName) {
            return self();
        }

        @Override
        public S patronymic(String patronymic) {
            return self();
        }

        @Override
        public S address(Address address) {
            return self();
        }

        @Override
        public S phones(List<String> phones) {
            return self();
        }

        @Override
        public S email(String email) {
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
