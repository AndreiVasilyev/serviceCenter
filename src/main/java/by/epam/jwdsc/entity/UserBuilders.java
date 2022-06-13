package by.epam.jwdsc.entity;

import java.util.List;

/**
 * The type User builders.
 */
public class UserBuilders {

    /**
     * New client client builder.
     *
     * @return the client builder
     */
    public static ClientBuilder newClient() {
        return new ClientBuilder();
    }

    /**
     * New employee employee builder.
     *
     * @return the employee builder
     */
    public static EmployeeBuilder newEmployee() {
        return new EmployeeBuilder();
    }

    /**
     * The type Employee builder.
     */
    public static class EmployeeBuilder extends AbstractUserBuilder<EmployeeBuilder, Employee> {

        private String login;
        private String password;

        /**
         * Login employee builder.
         *
         * @param login the login
         * @return the employee builder
         */
        public EmployeeBuilder login(String login) {
            this.login = login;
            return this;
        }

        /**
         * Password employee builder.
         *
         * @param password the password
         * @return the employee builder
         */
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

    /**
     * The type Client builder.
     */
    public static class ClientBuilder extends AbstractUserBuilder<ClientBuilder, Client> {

        private int discount;

        /**
         * Discount client builder.
         *
         * @param discount the discount
         * @return the client builder
         */
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

    /**
     * The type Abstract user builder.
     *
     * @param <S> the type parameter
     * @param <T> the type parameter
     */
    abstract static class AbstractUserBuilder<S extends UserBuilder, T extends AbstractUser> implements UserBuilder<S, T> {
        /**
         * The Id.
         */
        long id;
        /**
         * The First name.
         */
        String firstName;
        /**
         * The Second name.
         */
        String secondName;
        /**
         * The Patronymic.
         */
        String patronymic;
        /**
         * The Address.
         */
        Address address;
        /**
         * The Phones.
         */
        List<String> phones;
        /**
         * The Email.
         */
        String email;
        /**
         * The User role.
         */
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

        /**
         * Inner build t.
         *
         * @return the t
         */
        protected abstract T innerBuild();

        /**
         * Self s.
         *
         * @return the s
         */
        S self() {
            return (S) this;
        }
    }
}
