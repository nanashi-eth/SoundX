package Controller;

import Exceptions.AppErrors;
import Exceptions.ErrorLogger;
import Exceptions.MyException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ResultSetManager {

    public boolean isEmpty(ResultSet rs) throws MyException {
        try {
            if (rs.next()) {
                rs.beforeFirst();
                return false;
            } else {
                return true;
            }
        } catch (SQLException ex) {
            int errorCode = AppErrors.INVALID_RECORD_ID;
            ErrorLogger.getInstance().logError(errorCode);
            throw new MyException(errorCode);
        }
    }

    public boolean previousResult(ResultSet rs) throws MyException {
        try {
            return rs.previous();
        } catch (SQLException ex) {
            int errorCode = AppErrors.INVALID_RECORD_ID;
            ErrorLogger.getInstance().logError(errorCode);
            throw new MyException(errorCode);
        }
    }

    public boolean nextResult(ResultSet rs) throws MyException {
        try {
            return rs.next();
        } catch (SQLException ex) {
            int errorCode = AppErrors.INVALID_RECORD_ID;
            ErrorLogger.getInstance().logError(errorCode);
            throw new MyException(errorCode);
        }
    }

    public boolean firstResult(ResultSet rs) throws MyException {
        try {
            return rs.first();
        } catch (SQLException ex) {
            int errorCode = AppErrors.INVALID_RECORD_ID;
            ErrorLogger.getInstance().logError(errorCode);
            throw new MyException(errorCode);
        }
    }

    public boolean lastResult(ResultSet rs) throws MyException {
        try {
            return rs.last();
        } catch (SQLException ex) {
            int errorCode = AppErrors.INVALID_RECORD_ID;
            ErrorLogger.getInstance().logError(errorCode);
            throw new MyException(errorCode);
        }
    }

    public boolean isLast(ResultSet rs) throws MyException {
        try {
            return rs.isLast();
        } catch (SQLException ex) {
            int errorCode = AppErrors.INVALID_RECORD_ID;
            ErrorLogger.getInstance().logError(errorCode);
            throw new MyException(errorCode);
        }
    }

    public boolean isFirst(ResultSet rs) throws MyException {
        try {
            return rs.isFirst();
        } catch (SQLException ex) {
            int errorCode = AppErrors.INVALID_RECORD_ID;
            ErrorLogger.getInstance().logError(errorCode);
            throw new MyException(errorCode);
        }
    }

    public void delete(ResultSet rs) throws MyException{
        try {
            rs.deleteRow();
            ConnectionDB.getConnection().commit();
        } catch (SQLException ex) {
            int errorCode = AppErrors.SQL_DELETE_ERROR;
            ErrorLogger.getInstance().logError(errorCode);
            throw new MyException(errorCode);
        }
    }

    public void quit(ResultSet rs, PreparedStatement st){
        rs = null;
        st = null;
    }
}
