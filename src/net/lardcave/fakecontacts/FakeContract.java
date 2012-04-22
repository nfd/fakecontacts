package net.lardcave.fakecontacts;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;

public class FakeContract {
	public static final String AUTHORITY = "net.lardcave.fakecontacts";
    /** A content:// style uri to the authority for the contacts provider */
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);
    
    public static final Uri CONTACTS_CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, "contacts");
    public static final Uri CONTACTS_CONTENT_FILTER_URI = Uri.withAppendedPath(CONTACTS_CONTENT_URI, "filter");
    public static final Uri CONTACTS_CONTENT_LOOKUP_URI = Uri.withAppendedPath(CONTACTS_CONTENT_URI, "lookup");
    public static final Uri RAW_CONTACTS_CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, "raw_contacts");
    public static final Uri CONTACTS_CONTENT_VCARD_URI = Uri.withAppendedPath(CONTACTS_CONTENT_URI, "as_vcard");
    public static final Uri DATA_CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, "data");
    public static final Uri RAW_CONTACT_ENTITIES_CONTENT_URI =
            Uri.withAppendedPath(AUTHORITY_URI, "raw_contact_entities");
    public static final Uri GROUPS_CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, "groups");
    public static final Uri SETTINGS_CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, "settings");
    public static final Uri PHONELOOKUP_CONTENT_FILTER_URI = Uri.withAppendedPath(AUTHORITY_URI, "phone_lookup");
    
    // Common data kinds
    public static final Uri CDK_PHONE_CONTENT_URI = Uri.withAppendedPath(DATA_CONTENT_URI, "phones");
    public static final Uri CDK_PHONE_CONTENT_FILTER_URI = Uri.withAppendedPath(CDK_PHONE_CONTENT_URI, "filter");
    public static final Uri CDK_EMAIL_CONTENT_URI = Uri.withAppendedPath(DATA_CONTENT_URI, "emails");
    public static final Uri CDK_EMAIL_CONTENT_LOOKUP_URI = Uri.withAppendedPath(CDK_EMAIL_CONTENT_URI, "lookup");
    public static final Uri CDK_EMAIL_CONTENT_FILTER_URI = Uri.withAppendedPath(CDK_EMAIL_CONTENT_URI, "filter");
    public static final Uri CDK_STRUCTUREDPOSTAL_CONTENT_URI = Uri.withAppendedPath(DATA_CONTENT_URI, "postals");
    

    public static final String CALL_LOG_AUTHORITY = "call_log";
    public static final Uri CALL_LOG_CONTENT_URI = Uri.parse("content://" + CALL_LOG_AUTHORITY);
    
    public static final String LEGACY_AUTHORITY = "fakecontacts";
    public static final Uri LEGACY_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    
    public static Uri Contacts_getLookupUri(long contactId, String lookupKey) {
        return ContentUris.withAppendedId(Uri.withAppendedPath(CONTACTS_CONTENT_LOOKUP_URI,
                lookupKey), contactId);
    }
    
    public static Uri RawContacts_getContactLookupUri(ContentResolver resolver, Uri rawContactUri) {
        // TODO: use a lighter query by joining rawcontacts with contacts in provider
        final Uri dataUri = Uri.withAppendedPath(rawContactUri, "data");
        final Cursor cursor = resolver.query(dataUri, new String[] {
                "contact_id", "lookup"
        }, null, null, null);

        Uri lookupUri = null;
        try {
            if (cursor != null && cursor.moveToFirst()) {
                final long contactId = cursor.getLong(0);
                final String lookupKey = cursor.getString(1);
                return Contacts_getLookupUri(contactId, lookupKey);
            }
        } finally {
            if (cursor != null) cursor.close();
        }
        return lookupUri;
    }
}
