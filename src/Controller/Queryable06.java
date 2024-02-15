package Controller;

public interface Queryable06 {

    String GET_CLIENT_TABLE = "SELECT * "
            + "FROM Client";

    String GET_ALL_CLIENT_INFO = "SELECT * "
            + "FROM Client WHERE clientID = ?";

    String GET_ALL_BANKACCOUNT_INFO = "SELECT BankAccount.* "
            + "FROM ClientAccount "
            + "JOIN BankAccount ON "
            + "ClientAccount.accountID = "
            + "BankAccount.accountID "
            + "WHERE ClientAccount.clientID = ?";

    String GET_BANKACCOUNT_INFO = "SELECT * "
            + "FROM BankAccount "
            + "WHERE accountID = ?";

    String GET_ALL_TRANSACTION_INFO = "SELECT Transaction.* "
            + "FROM AccountTransaction "
            + "JOIN Transaction ON "
            + "AccountTransaction.transactionID = "
            + "Transaction.transactionID "
            + "WHERE AccountTransaction.accountID = ?";

    String GET_TRANSACTION_INFO = "SELECT * "
            + "FROM Transaction "
            + "WHERE transactionID = ?";
    
    String GET_ADDRESS_TABLE = "SELECT * "
            + "FROM Address ";

    String GET_ALL_ADDRESS_INFO = "SELECT Address.* "
            + "FROM ClientAddress "
            + "JOIN Address "
            + "ON ClientAddress.addressID = Address.addressID "
            + "WHERE ClientAddress.clientID = ?";

    String GET_CLIENTACCOUNT_TABLE = "SELECT * "
            + "FROM ClientAccount ";

    String GET_ALL_CLIENTACCOUNT_INFO = "SELECT * "
            + "FROM ClientAccount "
            + "WHERE clientID = ?";

    String GET_CLIENTACCOUNT_INFO = "SELECT * "
            + "FROM ClientAccount "
            + "WHERE clientID = ? AND accountID = ?";

    String GET_CLIENTADDRESS_TABLE = "SELECT * "
            + "FROM ClientAddress";
    
    String GET_CLIENTADDRESS_OF_CLIENT = "SELECT * "
            + "FROM ClientAddress "
            + "WHERE clientID = ?";

    String GET_ALL_CLIENTADDRESS_INFO = "SELECT * "
            + "FROM ClientAddress "
            + "WHERE clientID = ? AND addressID = ?";

    String GET_ALL_ACCOUNTTRANSACTION_INFO = "SELECT * "
            + "FROM AccountTransaction "
            + "WHERE accountID = ? AND transactionID = ?";
}
