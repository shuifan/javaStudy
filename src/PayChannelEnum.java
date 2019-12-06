public enum PayChannelEnum {

    /**
     * 支付宝生活号
     */
    ALIPAY_MOBILE("ZFB001"),

    /**
     * 微信公众号
     */
    WX_JSAPI("WX001");

    private final String extUserId;

    PayChannelEnum(String extUserId){
        this.extUserId = extUserId;
    }

    public String getExtUserId(){
        return extUserId;
    }
}
