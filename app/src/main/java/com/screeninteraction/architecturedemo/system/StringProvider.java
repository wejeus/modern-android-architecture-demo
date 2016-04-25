package com.screeninteraction.architecturedemo.system;

/**
 * This is intended to be used with Providers as a mean to be independent
 * from Android resources system and thus to be able to test providers.
 */
public interface StringProvider {

    String getString(int stringId);

}
