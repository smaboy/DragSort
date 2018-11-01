package com.example.smaboy.dragsort.bean;

import java.util.List;

/**
 * 类名: MoreService
 * 类作用描述: java类作用描述
 * 作者: Smaboy
 * 创建时间: 2018/10/26 16:24
 */
public class MoreService {

    /**
     * title : 更多服务
     * edit : 编辑
     * cancel : 取消
     * finish : 完成
     * edit_my_service : 编辑我的服务
     * defaul_sort : {"title":"默认排序","before":{"title":"行前","services":[{"title":"校园行","url":"https://qqe2.com","imgUrl":""},{"title":"优惠专区","url":"https://qqe2.com","imgUrl":""},{"title":"境外wifi","url":"https://qqe2.com","imgUrl":""},{"title":"店面点餐","url":"https://qqe2.com","imgUrl":""},{"title":"逾重行李","url":"https://qqe2.com","imgUrl":""},{"title":"特殊旅客","url":"https://qqe2.com","imgUrl":""},{"title":"信息服务","url":"https://qqe2.com","imgUrl":""},{"title":"保险专区","url":"https://qqe2.com","imgUrl":""},{"title":"惠价直销","url":"https://qqe2.com","imgUrl":""},{"title":"优选座位","url":"https://qqe2.com","imgUrl":""},{"title":"客票验真","url":"https://qqe2.com","imgUrl":""},{"title":"旅游度假","url":"https://qqe2.com","imgUrl":""},{"title":"机票+酒店","url":"https://qqe2.com","imgUrl":""},{"title":"机场接送","url":"https://qqe2.com","imgUrl":""},{"title":"酒店预订","url":"https://qqe2.com","imgUrl":""},{"title":"预办登机","url":"https://qqe2.com","imgUrl":""},{"title":"公告","url":"https://qqe2.com","imgUrl":""},{"title":"迪士尼航班","url":"https://qqe2.com","imgUrl":""}]},"middle":{"title":"行中","services":[{"title":"航班动态","url":"https://qqe2.com","imgUrl":""},{"title":"机场服务","url":"https://qqe2.com","imgUrl":""},{"title":"改期退票","url":"https://qqe2.com","imgUrl":""},{"title":"机上WI-FI","url":"https://qqe2.com","imgUrl":""},{"title":"代泊车","url":"https://qqe2.com","imgUrl":""}]},"behind":{"title":"行后","services":[{"title":"会员服务","url":"https://qqe2.com","imgUrl":""},{"title":"意见反馈","url":"https://qqe2.com","imgUrl":""},{"title":"航延取消","url":"https://qqe2.com","imgUrl":""},{"title":"积分商城","url":"https://qqe2.com","imgUrl":""},{"title":"企业客户","url":"https://qqe2.com","imgUrl":""}]}}
     * intelligent_sort : {"title":"智能排序","services":{"title":"智能排序","services":[{"title":"校园行","url":"https://qqe2.com","imgUrl":""},{"title":"优惠专区","url":"https://qqe2.com","imgUrl":""},{"title":"境外wifi","url":"https://qqe2.com","imgUrl":""},{"title":"店面点餐","url":"https://qqe2.com","imgUrl":""},{"title":"逾重行李","url":"https://qqe2.com","imgUrl":""},{"title":"特殊旅客","url":"https://qqe2.com","imgUrl":""},{"title":"信息服务","url":"https://qqe2.com","imgUrl":""},{"title":"保险专区","url":"https://qqe2.com","imgUrl":""},{"title":"惠价直销","url":"https://qqe2.com","imgUrl":""},{"title":"优选座位","url":"https://qqe2.com","imgUrl":""},{"title":"客票验真","url":"https://qqe2.com","imgUrl":""},{"title":"旅游度假","url":"https://qqe2.com","imgUrl":""},{"title":"机票+酒店","url":"https://qqe2.com","imgUrl":""},{"title":"机场接送","url":"https://qqe2.com","imgUrl":""},{"title":"酒店预订","url":"https://qqe2.com","imgUrl":""},{"title":"预办登机","url":"https://qqe2.com","imgUrl":""},{"title":"公告","url":"https://qqe2.com","imgUrl":""},{"title":"迪士尼航班","url":"https://qqe2.com","imgUrl":""},{"title":"航班动态","url":"https://qqe2.com","imgUrl":""},{"title":"机场服务","url":"https://qqe2.com","imgUrl":""},{"title":"改期退票","url":"https://qqe2.com","imgUrl":""},{"title":"机上WI-FI","url":"https://qqe2.com","imgUrl":""},{"title":"代泊车","url":"https://qqe2.com","imgUrl":""},{"title":"会员服务","url":"https://qqe2.com","imgUrl":""},{"title":"意见反馈","url":"https://qqe2.com","imgUrl":""},{"title":"航延取消","url":"https://qqe2.com","imgUrl":""},{"title":"积分商城","url":"https://qqe2.com","imgUrl":""},{"title":"企业客户","url":"https://qqe2.com","imgUrl":""}]}}
     * myService : {"title":"我的服务","title_tip":"(按住拖动调整排序)","services":[{"title":"境外wifi","url":"https://qqe2.com","imgUrl":""},{"title":"优惠专区","url":"https://qqe2.com","imgUrl":""},{"title":"地面点餐","url":"https://qqe2.com/","imgUrl":""},{"title":"会员服务","url":"https://qqe2.com","imgUrl":"\"\""},{"title":"航班动态","url":"https://qqe2.com/","imgUrl":""}]}
     */

    private String title;
    private String edit;
    private String cancel;
    private String finish;
    private String edit_my_service;
    private DefaulSortBean defaul_sort;
    private IntelligentSortBean intelligent_sort;
    private MyServiceBean myService;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public String getCancel() {
        return cancel;
    }

    public void setCancel(String cancel) {
        this.cancel = cancel;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public String getEdit_my_service() {
        return edit_my_service;
    }

    public void setEdit_my_service(String edit_my_service) {
        this.edit_my_service = edit_my_service;
    }

    public DefaulSortBean getDefaul_sort() {
        return defaul_sort;
    }

    public void setDefaul_sort(DefaulSortBean defaul_sort) {
        this.defaul_sort = defaul_sort;
    }

    public IntelligentSortBean getIntelligent_sort() {
        return intelligent_sort;
    }

    public void setIntelligent_sort(IntelligentSortBean intelligent_sort) {
        this.intelligent_sort = intelligent_sort;
    }

    public MyServiceBean getMyService() {
        return myService;
    }

    public void setMyService(MyServiceBean myService) {
        this.myService = myService;
    }

    public static class DefaulSortBean {
        /**
         * title : 默认排序
         * before : {"title":"行前","services":[{"title":"校园行","url":"https://qqe2.com","imgUrl":""},{"title":"优惠专区","url":"https://qqe2.com","imgUrl":""},{"title":"境外wifi","url":"https://qqe2.com","imgUrl":""},{"title":"店面点餐","url":"https://qqe2.com","imgUrl":""},{"title":"逾重行李","url":"https://qqe2.com","imgUrl":""},{"title":"特殊旅客","url":"https://qqe2.com","imgUrl":""},{"title":"信息服务","url":"https://qqe2.com","imgUrl":""},{"title":"保险专区","url":"https://qqe2.com","imgUrl":""},{"title":"惠价直销","url":"https://qqe2.com","imgUrl":""},{"title":"优选座位","url":"https://qqe2.com","imgUrl":""},{"title":"客票验真","url":"https://qqe2.com","imgUrl":""},{"title":"旅游度假","url":"https://qqe2.com","imgUrl":""},{"title":"机票+酒店","url":"https://qqe2.com","imgUrl":""},{"title":"机场接送","url":"https://qqe2.com","imgUrl":""},{"title":"酒店预订","url":"https://qqe2.com","imgUrl":""},{"title":"预办登机","url":"https://qqe2.com","imgUrl":""},{"title":"公告","url":"https://qqe2.com","imgUrl":""},{"title":"迪士尼航班","url":"https://qqe2.com","imgUrl":""}]}
         * middle : {"title":"行中","services":[{"title":"航班动态","url":"https://qqe2.com","imgUrl":""},{"title":"机场服务","url":"https://qqe2.com","imgUrl":""},{"title":"改期退票","url":"https://qqe2.com","imgUrl":""},{"title":"机上WI-FI","url":"https://qqe2.com","imgUrl":""},{"title":"代泊车","url":"https://qqe2.com","imgUrl":""}]}
         * behind : {"title":"行后","services":[{"title":"会员服务","url":"https://qqe2.com","imgUrl":""},{"title":"意见反馈","url":"https://qqe2.com","imgUrl":""},{"title":"航延取消","url":"https://qqe2.com","imgUrl":""},{"title":"积分商城","url":"https://qqe2.com","imgUrl":""},{"title":"企业客户","url":"https://qqe2.com","imgUrl":""}]}
         */

        private String title;
        private BeforeBean before;
        private MiddleBean middle;
        private BehindBean behind;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public BeforeBean getBefore() {
            return before;
        }

        public void setBefore(BeforeBean before) {
            this.before = before;
        }

        public MiddleBean getMiddle() {
            return middle;
        }

        public void setMiddle(MiddleBean middle) {
            this.middle = middle;
        }

        public BehindBean getBehind() {
            return behind;
        }

        public void setBehind(BehindBean behind) {
            this.behind = behind;
        }

        public static class BeforeBean {
            /**
             * title : 行前
             * services : [{"title":"校园行","url":"https://qqe2.com","imgUrl":""},{"title":"优惠专区","url":"https://qqe2.com","imgUrl":""},{"title":"境外wifi","url":"https://qqe2.com","imgUrl":""},{"title":"店面点餐","url":"https://qqe2.com","imgUrl":""},{"title":"逾重行李","url":"https://qqe2.com","imgUrl":""},{"title":"特殊旅客","url":"https://qqe2.com","imgUrl":""},{"title":"信息服务","url":"https://qqe2.com","imgUrl":""},{"title":"保险专区","url":"https://qqe2.com","imgUrl":""},{"title":"惠价直销","url":"https://qqe2.com","imgUrl":""},{"title":"优选座位","url":"https://qqe2.com","imgUrl":""},{"title":"客票验真","url":"https://qqe2.com","imgUrl":""},{"title":"旅游度假","url":"https://qqe2.com","imgUrl":""},{"title":"机票+酒店","url":"https://qqe2.com","imgUrl":""},{"title":"机场接送","url":"https://qqe2.com","imgUrl":""},{"title":"酒店预订","url":"https://qqe2.com","imgUrl":""},{"title":"预办登机","url":"https://qqe2.com","imgUrl":""},{"title":"公告","url":"https://qqe2.com","imgUrl":""},{"title":"迪士尼航班","url":"https://qqe2.com","imgUrl":""}]
             */

            private String title;
            private List<ServiceBean> services;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<ServiceBean> getServices() {
                return services;
            }

            public void setServices(List<ServiceBean> services) {
                this.services = services;
            }

        }

        public static class MiddleBean {
            /**
             * title : 行中
             * services : [{"title":"航班动态","url":"https://qqe2.com","imgUrl":""},{"title":"机场服务","url":"https://qqe2.com","imgUrl":""},{"title":"改期退票","url":"https://qqe2.com","imgUrl":""},{"title":"机上WI-FI","url":"https://qqe2.com","imgUrl":""},{"title":"代泊车","url":"https://qqe2.com","imgUrl":""}]
             */

            private String title;
            private List<ServiceBean> services;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<ServiceBean> getServices() {
                return services;
            }

            public void setServices(List<ServiceBean> services) {
                this.services = services;
            }


        }

        public static class BehindBean {
            /**
             * title : 行后
             * services : [{"title":"会员服务","url":"https://qqe2.com","imgUrl":""},{"title":"意见反馈","url":"https://qqe2.com","imgUrl":""},{"title":"航延取消","url":"https://qqe2.com","imgUrl":""},{"title":"积分商城","url":"https://qqe2.com","imgUrl":""},{"title":"企业客户","url":"https://qqe2.com","imgUrl":""}]
             */

            private String title;
            private List<ServiceBean> services;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<ServiceBean> getServices() {
                return services;
            }

            public void setServices(List<ServiceBean> services) {
                this.services = services;
            }

        }
    }

    public static class IntelligentSortBean {
        /**
         * title : 智能排序
         * services : {"title":"智能排序","services":[{"title":"校园行","url":"https://qqe2.com","imgUrl":""},{"title":"优惠专区","url":"https://qqe2.com","imgUrl":""},{"title":"境外wifi","url":"https://qqe2.com","imgUrl":""},{"title":"店面点餐","url":"https://qqe2.com","imgUrl":""},{"title":"逾重行李","url":"https://qqe2.com","imgUrl":""},{"title":"特殊旅客","url":"https://qqe2.com","imgUrl":""},{"title":"信息服务","url":"https://qqe2.com","imgUrl":""},{"title":"保险专区","url":"https://qqe2.com","imgUrl":""},{"title":"惠价直销","url":"https://qqe2.com","imgUrl":""},{"title":"优选座位","url":"https://qqe2.com","imgUrl":""},{"title":"客票验真","url":"https://qqe2.com","imgUrl":""},{"title":"旅游度假","url":"https://qqe2.com","imgUrl":""},{"title":"机票+酒店","url":"https://qqe2.com","imgUrl":""},{"title":"机场接送","url":"https://qqe2.com","imgUrl":""},{"title":"酒店预订","url":"https://qqe2.com","imgUrl":""},{"title":"预办登机","url":"https://qqe2.com","imgUrl":""},{"title":"公告","url":"https://qqe2.com","imgUrl":""},{"title":"迪士尼航班","url":"https://qqe2.com","imgUrl":""},{"title":"航班动态","url":"https://qqe2.com","imgUrl":""},{"title":"机场服务","url":"https://qqe2.com","imgUrl":""},{"title":"改期退票","url":"https://qqe2.com","imgUrl":""},{"title":"机上WI-FI","url":"https://qqe2.com","imgUrl":""},{"title":"代泊车","url":"https://qqe2.com","imgUrl":""},{"title":"会员服务","url":"https://qqe2.com","imgUrl":""},{"title":"意见反馈","url":"https://qqe2.com","imgUrl":""},{"title":"航延取消","url":"https://qqe2.com","imgUrl":""},{"title":"积分商城","url":"https://qqe2.com","imgUrl":""},{"title":"企业客户","url":"https://qqe2.com","imgUrl":""}]}
         */

        private String title;
        private ServicesBeanXXXX services;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public ServicesBeanXXXX getServices() {
            return services;
        }

        public void setServices(ServicesBeanXXXX services) {
            this.services = services;
        }

        public static class ServicesBeanXXXX {
            /**
             * title : 智能排序
             * services : [{"title":"校园行","url":"https://qqe2.com","imgUrl":""},{"title":"优惠专区","url":"https://qqe2.com","imgUrl":""},{"title":"境外wifi","url":"https://qqe2.com","imgUrl":""},{"title":"店面点餐","url":"https://qqe2.com","imgUrl":""},{"title":"逾重行李","url":"https://qqe2.com","imgUrl":""},{"title":"特殊旅客","url":"https://qqe2.com","imgUrl":""},{"title":"信息服务","url":"https://qqe2.com","imgUrl":""},{"title":"保险专区","url":"https://qqe2.com","imgUrl":""},{"title":"惠价直销","url":"https://qqe2.com","imgUrl":""},{"title":"优选座位","url":"https://qqe2.com","imgUrl":""},{"title":"客票验真","url":"https://qqe2.com","imgUrl":""},{"title":"旅游度假","url":"https://qqe2.com","imgUrl":""},{"title":"机票+酒店","url":"https://qqe2.com","imgUrl":""},{"title":"机场接送","url":"https://qqe2.com","imgUrl":""},{"title":"酒店预订","url":"https://qqe2.com","imgUrl":""},{"title":"预办登机","url":"https://qqe2.com","imgUrl":""},{"title":"公告","url":"https://qqe2.com","imgUrl":""},{"title":"迪士尼航班","url":"https://qqe2.com","imgUrl":""},{"title":"航班动态","url":"https://qqe2.com","imgUrl":""},{"title":"机场服务","url":"https://qqe2.com","imgUrl":""},{"title":"改期退票","url":"https://qqe2.com","imgUrl":""},{"title":"机上WI-FI","url":"https://qqe2.com","imgUrl":""},{"title":"代泊车","url":"https://qqe2.com","imgUrl":""},{"title":"会员服务","url":"https://qqe2.com","imgUrl":""},{"title":"意见反馈","url":"https://qqe2.com","imgUrl":""},{"title":"航延取消","url":"https://qqe2.com","imgUrl":""},{"title":"积分商城","url":"https://qqe2.com","imgUrl":""},{"title":"企业客户","url":"https://qqe2.com","imgUrl":""}]
             */

            private String title;
            private List<ServiceBean> services;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<ServiceBean> getServices() {
                return services;
            }

            public void setServices(List<ServiceBean> services) {
                this.services = services;
            }

            public static class ServicesBeanXXX {
                /**
                 * title : 校园行
                 * url : https://qqe2.com
                 * imgUrl :
                 */

                private String title;
                private String url;
                private String imgUrl;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getImgUrl() {
                    return imgUrl;
                }

                public void setImgUrl(String imgUrl) {
                    this.imgUrl = imgUrl;
                }
            }
        }
    }

    public static class MyServiceBean {
        /**
         * title : 我的服务
         * title_tip : (按住拖动调整排序)
         * services : [{"title":"境外wifi","url":"https://qqe2.com","imgUrl":""},{"title":"优惠专区","url":"https://qqe2.com","imgUrl":""},{"title":"地面点餐","url":"https://qqe2.com/","imgUrl":""},{"title":"会员服务","url":"https://qqe2.com","imgUrl":"\"\""},{"title":"航班动态","url":"https://qqe2.com/","imgUrl":""}]
         */

        private String title;
        private String title_tip;
        private List<ServiceBean> services;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle_tip() {
            return title_tip;
        }

        public void setTitle_tip(String title_tip) {
            this.title_tip = title_tip;
        }

        public List<ServiceBean> getServices() {
            return services;
        }

        public void setServices(List<ServiceBean> services) {
            this.services = services;
        }

    }
}
