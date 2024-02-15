package Controller;

import Exceptions.AppErrors;
import Exceptions.ErrorLogger;
import Exceptions.MyException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ResultSetManager {

    protected void handleSQLException(int errorCode) throws MyException {
        ErrorLogger.getInstance().logError(errorCode);
        throw new MyException(errorCode);
    }

    protected void handleDeleteSQLException(SQLException ex) throws MyException {
        int errorCode = AppErrors.SQL_DELETE_ERROR;
        ErrorLogger.getInstance().logError(errorCode);
        throw new MyException(errorCode);
    }

    protected boolean moveResultSetCursor(ResultSet rs, ResultSetMovement movement) throws MyException {
        try {
            switch (movement) {
                case FIRST:
                    return rs.first();
                case LAST:
                    return rs.last();
                case NEXT:
                    return rs.next();
                case PREVIOUS:
                    return rs.previous();
                default:
                    return false;
            }
        } catch (SQLException ex) {
            handleSQLException(101);
            return false;
        }
    }

    protected boolean checkResultSetPosition(ResultSet rs, ResultSetPosition position) throws MyException {
        try {
            switch (position) {
                case IS_FIRST:
                    return rs.isFirst();
                case IS_LAST:
                    return rs.isLast();
                default:
                    return false;
            }
        } catch (SQLException ex) {
            handleSQLException(101);
            return false;
        }
    }

    protected void deleteCurrentRow(ResultSet rs) throws MyException {
        try {
            rs.deleteRow();
            ConnectionDB.getConnection().commit();
        } catch (SQLException ex) {
            handleDeleteSQLException(ex);
        }
    }

    protected void closeResultSetAndStatement(ResultSet rs, PreparedStatement st) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
        } catch (SQLException ex) {
            // Log or handle the exception as needed
        }
    }

    // Enumerations for movement and position of ResultSet
    protected enum ResultSetMovement {
        FIRST, LAST, NEXT, PREVIOUS
    }

    protected enum ResultSetPosition {
        IS_FIRST, IS_LAST
    }
}
