package com.xiayiye.takeout.model.beans

/**
 * @author xiayiye
 */
class ResponseInfo {
    //服务器开发者定义的数据结构
    var code: String = ""
    var data: ResponseData? = null
    /*
    下面是外卖首页的所有Json数据
    {
  "code": "0",
  "data": {
    "categorieList": [
      {
        "id": 1,
        "name": "李先生牛肉面",
        "pic": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1583404846461&di=bac0f553cc3058cdabdeb685b912738e&imgtype=0&src=http%3A%2F%2Fimg.sccnn.com%2Fbimg%2F338%2F51218.jpg"
      },
      {
        "id": 1,
        "name": "李先生牛肉面",
        "pic": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1583404846461&di=bac0f553cc3058cdabdeb685b912738e&imgtype=0&src=http%3A%2F%2Fimg.sccnn.com%2Fbimg%2F338%2F51218.jpg"
      }
    ],
    "nearbySellerList": [
      {
        "activityList": [

        ],
        "deliveryFee": "4",
        "distance": "200米/33分钟",
        "ensuer": "",
        "icon": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1583404846461&di=bac0f553cc3058cdabdeb685b912738e&imgtype=0&src=http%3A%2F%2Fimg.sccnn.com%2Fbimg%2F338%2F51218.jpg",
        "id": 1,
        "invoice": "",
        "name": "附近第1家分店",
        "pic": "",
        "recentVisit": "",
        "sale": "月售99份",
        "score": "5",
        "sendPrice": "20",
        "time": ""
      },
      {
        "activityList": [

        ],
        "deliveryFee": "4",
        "distance": "735米/43分钟",
        "ensuer": "",
        "icon": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1583404846461&di=bac0f553cc3058cdabdeb685b912738e&imgtype=0&src=http%3A%2F%2Fimg.sccnn.com%2Fbimg%2F338%2F51218.jpg",
        "id": 2,
        "invoice": "",
        "name": "附近第2家分店",
        "pic": "",
        "recentVisit": "",
        "sale": "月售3219份",
        "score": "5",
        "sendPrice": "18",
        "time": ""
      },
      {
        "activityList": [

        ],
        "deliveryFee": "2",
        "distance": "1239米/63分钟",
        "ensuer": "",
        "icon": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1583404846461&di=bac0f553cc3058cdabdeb685b912738e&imgtype=0&src=http%3A%2F%2Fimg.sccnn.com%2Fbimg%2F338%2F51218.jpg",
        "id": 3,
        "invoice": "",
        "name": "附近第3家分店",
        "pic": "",
        "recentVisit": "",
        "sale": "月售327份",
        "score": "3",
        "sendPrice": "13",
        "time": ""
      }
    ],
    "otherSellerList": [
      {
        "activityList": [

        ],
        "deliveryFee": "6",
        "distance": "2319米/73分钟",
        "ensuer": "",
        "icon": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1583404846461&di=bac0f553cc3058cdabdeb685b912738e&imgtype=0&src=http%3A%2F%2Fimg.sccnn.com%2Fbimg%2F338%2F51218.jpg",
        "id": 23,
        "invoice": "",
        "name": "其他第二十三家分店",
        "pic": "",
        "recentVisit": "",
        "sale": "月售89份",
        "score": "4",
        "sendPrice": "24",
        "time": ""
      },
      {
        "activityList": [

        ],
        "deliveryFee": "6",
        "distance": "996米/50分钟",
        "ensuer": "",
        "icon": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1583404846461&di=bac0f553cc3058cdabdeb685b912738e&imgtype=0&src=http%3A%2F%2Fimg.sccnn.com%2Fbimg%2F338%2F51218.jpg",
        "id": 24,
        "invoice": "",
        "name": "其他第二十四家分店",
        "pic": "",
        "recentVisit": "",
        "sale": "月售8份",
        "score": "4",
        "sendPrice": "30",
        "time": ""
      }
    ],
    "promotionList": [
      {
        "id": 1,
        "info": "促销信息1",
        "pic": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1583404846461&di=bac0f553cc3058cdabdeb685b912738e&imgtype=0&src=http%3A%2F%2Fimg.sccnn.com%2Fbimg%2F338%2F51218.jpg"
      },
      {
        "id": 2,
        "info": "促销信息2",
        "pic": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1583404846461&di=bac0f553cc3058cdabdeb685b912738e&imgtype=0&src=http%3A%2F%2Fimg.sccnn.com%2Fbimg%2F338%2F51218.jpg"
      },
      {
        "id": 3,
        "info": "促销信息3",
        "pic": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1583404846461&di=bac0f553cc3058cdabdeb685b912738e&imgtype=0&src=http%3A%2F%2Fimg.sccnn.com%2Fbimg%2F338%2F51218.jpg"
      }
    ]
  }
}
    */
}
