package com.dolphinpay.server.rest_api.v1;

public class UtilsV1 {
    public static final String SERVER_URL = "http://dolphinpaysv.us-west-2.elasticbeanstalk.com";
    public static final String BASE_REST_API_PATH = "/dolphinpayREST-API";
    public static final String BASE_VERSION = "/v1";
    public static final String BASE_URL = BASE_REST_API_PATH + BASE_VERSION;
    public static final String BASE_FULL_LINK = SERVER_URL + BASE_URL;

    public static class URLS {
        public static final String authentication = "/auth";
        public static final String platformsAll = "/platforms/all";
        public static final String platformsPartnerships = "/platforms/partnerships";
        public static final String categoriesOfRoom = "/categories/{roomId}";
        public static final String categoriesOfStand = "/categories/stand/{standId}";
        public static final String deleteCategoryFromRoom = "/categories/{categoryId}/rooms/{roomId}";
        public static final String deletePlatformSubscriber = "/platforms/{platformId}/subscribers";
        public static final String addCategoryToRoom = "/categories/rooms";
        public static final String createRoomForStand = "/stands/rooms";
        public static final String createSubscriptionPlatform = "/platforms/subscriptions";
        public static final String standsRooms = "/stands/{id}/rooms";
        public static final String stands = "/stands";
        public static final String standsProducts = "/stands/{id}/products";
        public static final String roomSubscriptionCode = "/rooms/{id}/subscriptionCode";
    }
}
