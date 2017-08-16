package com.example.jacky.mydagger2.mvp.di.model.bean;

import java.util.List;

/**
 * Created by jacky on 2017/8/3.
 * banker developer. <br/>
 * <br/>
 */

public class Food {

    /**
     * page : 1
     * total_pages : 203
     * feeds : [{"item_id":11847,"title":"减脂餐","card_image":"http://one.boohee.cn/food/2017/7/11/938C4D36-E264-428C-87A7-CA08F766221E.jpg?imageView2/2/w/640","publisher":"Libra迷雾F","publisher_avatar":"http://tvax2.sinaimg.cn/crop.0.0.1241.1241.180/a3fa084dly8fdwy0rrip5j20yi0yhdm2.jpg","description":"200克樱桃番茄\n200克黄瓜\n50克紫薯\n120克蒸南瓜\n155克烤黄金鲽\n100克烤鸡胸","content_type":5,"type":"food_card","like_ct":303},{"item_id":11844,"title":"元气 鸡肉沙拉","card_image":"http://one.boohee.cn/food/2017/7/11/38880AC1-7D64-4E0B-9A82-801127C30BFA.jpg?imageView2/2/w/640","publisher":"xxxibgdrgn76083","publisher_avatar":"http://wx.qlogo.cn/mmopen/brsKjTrsruXg1GN8DPElYINyAqvicGz8eFrYFqg0UM1r8MxoE9yDiaYFjfZV3F4D8tddJOFCHthGPNs0C0pr0Z3ib8vdjdc2eicb/0","description":"田园沙拉的底➕上一些烟熏鸡肉面包丁配上蜂蜜芥末酱","content_type":5,"type":"food_card","like_ct":64},{"item_id":11788,"title":"自制健身早餐","card_image":"http://one.boohee.cn/food/2017/7/9/3F0F3E30-D246-49E3-B2B1-530AFCA6F677.jpg?imageView2/2/w/640","publisher":"7777seven_","publisher_avatar":"http://one.boohee.cn/a/2015/7/7/288188A2-E2B7-487F-8B14-DF9F4743E689.jpg","description":"Day1","content_type":5,"type":"food_card","like_ct":64},{"item_id":11785,"title":"紫薯","card_image":"http://one.boohee.cn/food/2017/7/9/8B4FB1F0-971C-429E-BFCE-C9872DBE8681.jpg?imageView2/2/w/640","publisher":"江户川黄蓉","publisher_avatar":"http://one.boohee.cn/t/2017/6/19/C10F077A-BBCF-43A5-9CD4-C3511045C18C.jpg","description":"","content_type":5,"type":"food_card","like_ct":34},{"item_id":11775,"title":"增肌","card_image":"http://one.boohee.cn/food/2017/7/8/D2676720-2F3B-4E8D-9D6F-0ADCCB21C49C.jpg?imageView2/2/w/640","publisher":"jablka","publisher_avatar":"http://one.boohee.cn/t/2017/7/8/316A6C58-B1BF-4640-AF07-93FD5FCA9034.jpg","description":"","content_type":5,"type":"food_card","like_ct":107},{"item_id":11774,"title":"双人餐","card_image":"http://one.boohee.cn/food/2017/07/08/64626e6b-4d9c-4ee8-93b4-d11b5baef555?imageView2/2/w/640","publisher":"帝都大贫僧","publisher_avatar":"http://tva4.sinaimg.cn/crop.0.0.640.640.1024/d46fea02jw8ev17sds34zj20hs0hsgm8.jpg","description":"两个人的健身餐❤","content_type":5,"type":"food_card","like_ct":77},{"item_id":11766,"title":"蚝油炒生菜","card_image":"http://one.boohee.cn/food/2017/7/8/F6955A73-C415-44A6-88B7-3A37497C6F8C.jpg?imageView2/2/w/640","publisher":"Diana.18210","publisher_avatar":"https://wx.qlogo.cn/mmopen/CE0wJibGEw0HJElbDHpomzZ17QnVyDWYKEkEOlOnU2ovOf78KcAQdliaq42XZNmEF7ugLM4xXwwUG3du5Fj8cibUxicj3zicpgkfz/0","description":"","content_type":5,"type":"food_card","like_ct":13},{"item_id":11761,"title":"鸡胸脯肉","card_image":"http://one.boohee.cn/food/2017/7/8/D3671DAE-FDF3-481F-B3FB-D38DCFE9E480.jpg?imageView2/2/w/640","publisher":"YYYan999.","publisher_avatar":"http://wx.qlogo.cn/mmopen/6KA0QCGAlFzO6v7fD5ciaomdZlxPorhzxDibyFd0Icz23HhP0mpTgnTlw98XVARt74ayicAWt4nGlG5ibYySACQPKlR4E2dPDoibg/0","description":"自制鸡脯面，肉需提前一晚腌制。","content_type":5,"type":"food_card","like_ct":40},{"item_id":11756,"title":"早餐","card_image":"http://one.boohee.cn/food/2017/7/8/6EE36714-2BAE-42CD-AE32-6C5D5492DE74.jpg?imageView2/2/w/640","publisher":"LLXUEHUA","publisher_avatar":null,"description":"我的早餐","content_type":5,"type":"food_card","like_ct":72},{"item_id":11752,"title":"荟食 早餐咖啡","card_image":"http://one.boohee.cn/food/2017/7/8/EF18125F-B6B7-47F5-9E0F-E5B7DA54CC95.jpg?imageView2/2/w/640","publisher":"danceM","publisher_avatar":"http://one.boohee.cn/t/2017/4/25/4B3642F0-C628-488F-A7CC-B438B03D9A91.jpg","description":"","content_type":5,"type":"food_card","like_ct":33}]
     */

    private String page;
    private int total_pages;
    private List<FeedsBean> feeds;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<FeedsBean> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<FeedsBean> feeds) {
        this.feeds = feeds;
    }

    public static class FeedsBean {
        /**
         * item_id : 11847
         * title : 减脂餐
         * card_image : http://one.boohee.cn/food/2017/7/11/938C4D36-E264-428C-87A7-CA08F766221E.jpg?imageView2/2/w/640
         * publisher : Libra迷雾F
         * publisher_avatar : http://tvax2.sinaimg.cn/crop.0.0.1241.1241.180/a3fa084dly8fdwy0rrip5j20yi0yhdm2.jpg
         * description : 200克樱桃番茄
         200克黄瓜
         50克紫薯
         120克蒸南瓜
         155克烤黄金鲽
         100克烤鸡胸
         * content_type : 5
         * type : food_card
         * like_ct : 303
         */

        private int item_id;
        private String title;
        private String card_image;
        private String publisher;
        private String publisher_avatar;
        private String description;
        private int content_type;
        private String type;
        private int like_ct;

        public int getItem_id() {
            return item_id;
        }

        public void setItem_id(int item_id) {
            this.item_id = item_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCard_image() {
            return card_image;
        }

        public void setCard_image(String card_image) {
            this.card_image = card_image;
        }

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public String getPublisher_avatar() {
            return publisher_avatar;
        }

        public void setPublisher_avatar(String publisher_avatar) {
            this.publisher_avatar = publisher_avatar;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getContent_type() {
            return content_type;
        }

        public void setContent_type(int content_type) {
            this.content_type = content_type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getLike_ct() {
            return like_ct;
        }

        public void setLike_ct(int like_ct) {
            this.like_ct = like_ct;
        }
    }
}
