package com.marvel.android.a1000salama.DataBase;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import Model.Governrate;

/**
 * Created by ahmed on 26/01/18.
 */

public class alfsamalaProvider extends ContentProvider {

    private static final UriMatcher mMatcher = buildUriMatcher();
    private alfSamalaSDBHelper mOpenHelper;

    static final int alfsamlma = 100;

    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = Contract.CONTENT_AUTHORITY;
        // For each type of URI you want to add, create a corresponding code.
        matcher.addURI(authority, Contract.PATH_MOVIE, alfsamlma);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new alfSamalaSDBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        Cursor retCursor;
        switch (mMatcher.match(uri)) {
            case alfsamlma: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        Contract.alfsalamaEntry.SystemMessagesTable, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Override
    public String getType(Uri uri) {
        final int match = mMatcher.match(uri);

        switch (match) {
            case alfsamlma:
                return Contract.alfsalamaEntry.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        Uri returnUri;

        switch (mMatcher.match(uri)) {
            case alfsamlma: {
                long _id = db.insert(Contract.alfsalamaEntry.SystemMessagesTable, null, values);
                if (_id > 0) {
                    returnUri = Contract.alfsalamaEntry.buildalfsalamaUri(_id);
                }
                else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int rowsDeleted;
        // this makes delete all rows return the number of rows deleted
        if (null == selection) selection = "1";
        switch (mMatcher.match(uri)) {
            case alfsamlma:
                rowsDeleted = db.delete(
                        Contract.alfsalamaEntry.SystemMessagesTable, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        // Because a null deletes all rows
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int rowsUpdated;

        switch (mMatcher.match(uri)) {
            case alfsamlma:
                rowsUpdated = db.update(Contract.alfsalamaEntry.SystemMessagesTable, values, selection,
                        selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }





}