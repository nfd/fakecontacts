Fake contacts provider
======================

This is a clone of Froyo's contacts provider, with all dependencies added.

It publishes at content://net.lardcave.fakecontacts rather than content://com.android.contacts. Having two contact providers on your device means that you can present the fake contacts list to applications which need access to your contacts, but which you don't trust. See http://code.lardcave.net/entries/2012/04/22/135057 for more details.

There are almost certainly loads of bugs. 

