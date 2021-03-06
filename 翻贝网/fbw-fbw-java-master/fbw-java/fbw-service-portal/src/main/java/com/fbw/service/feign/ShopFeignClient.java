package com.fbw.service.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fbw.service.config.feign.FeignDisableHystrixConfiguration;
import com.fbw.service.entity.portal.HomeReqEntity;
import com.fbw.service.entity.shop.InterviewParentEntity;
import com.fbw.service.entity.shop.RankFatherBusinEntity;
import com.fbw.service.entity.shop.RankSonBusinEntity;
import com.fbw.service.entity.shop.ShopBannerEntity;
import com.fbw.service.entity.shop.ShopClassBusinEntity;
import com.fbw.service.entity.shop.ShopDetailEntity;
import com.fbw.service.entity.shop.ShopDistrictEntity;
import com.fbw.service.entity.shop.ShopEventEntity;
import com.fbw.service.entity.shop.ShopFirstClassEntity;
import com.fbw.service.entity.shop.ShopHotWordEntity;
import com.fbw.service.entity.shop.ShopInfoEntity;
import com.fbw.service.entity.shop.ShopInfoErrorEntity;
import com.fbw.service.entity.shop.ShopQualityEntity;
import com.fbw.service.entity.shop.ShopSignEntity;

/**
 * 
 * <功能详细描述>shop套件feign客户端
 * @author FBW0115
 * @version [版本号, 2017年8月26日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@FeignClient(name = "fbw-service-shop", configuration = FeignDisableHystrixConfiguration.class)
public interface ShopFeignClient
{

    /**
     * 
     * <功能详细描述>查询首页分类列表
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/shop/getShopsCityBanner", method = RequestMethod.GET)
    List<ShopClassBusinEntity> getShopsClass(@RequestBody HomeReqEntity entity);

    /**
     * 
     * <功能详细描述>搜索
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/shop/getSearchShop", method = RequestMethod.GET)
    List<ShopInfoEntity> getSearchShop(@RequestBody HomeReqEntity entity);

    /**
     * 
     * <功能详细描述>根据城市id查询探店
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/shop/getInterviewByCityId", method = RequestMethod.GET)
    List<InterviewParentEntity> getInterviewByCityId(@RequestParam("cityId") int cityId,
            @RequestParam("page") int page);

    /**
     * 
     * <功能详细描述>根据探店id查询提到的店
     * @param interviewId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/shop/getInterviewChild", method = RequestMethod.GET)
    List<ShopInfoEntity> getInterviewChild(@RequestParam("interviewId") int interviewId);

    /**
     * 
     * <功能详细描述>根据城市id查询首页banner
     * @param CityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/shop/getBannerByCityId", method = RequestMethod.GET)
    List<ShopBannerEntity> getBannerByCityId(@RequestBody HomeReqEntity entity);

    /**
     * 
     * <功能详细描述>首页根据城市id查询品质好店
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/shop/getShopsQualityByCityId", method = RequestMethod.GET)
    List<ShopQualityEntity> getShopsQualityByCityId(@RequestBody HomeReqEntity entity);

    /**
     * 
     * <功能详细描述>根据城市id查询品质好店列表分页
     * @param cityId
     * @param begin
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/shop/getShopsQualityAll", method = RequestMethod.GET)
    List<ShopQualityEntity> getShopsQualityAll(@RequestBody HomeReqEntity entity);

    /**
     * 
     * <功能详细描述>查询附近的店
     * @param latItude 纬度
     * @param longItude 经度
     * @param page 分页，0开始
     * @return
     * @see [类、类#方法、类#成员]
     */
    // @RequestMapping(value = "/shop/getNearbyShops", method =
    // RequestMethod.GET)
    // List<ShopsInfoEntity> getNearbyShops(@RequestParam Map<String, Object>
    // map);

    /**
     * 
     * <功能详细描述>筛选
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/shop/getScreenShops", method = RequestMethod.GET)
    List<ShopInfoEntity> getScreenShops(@RequestBody HomeReqEntity entity);

    /**
     * 
     * <功能详细描述>根据shopid查看商户详情
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/shop/getShopsDetailByShopid", method = RequestMethod.GET)
    ShopDetailEntity getShopsDetailByShopid(@RequestBody HomeReqEntity entity);

    /**
     * 
     * <功能详细描述>查询翻倍日
     * @param map(shopId、date)
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/shop/getShopsEventsByDate", method = RequestMethod.GET)
    List<ShopEventEntity> getShopsEventsByDate(@RequestBody HomeReqEntity entity);

    /**
     * 
     * <功能详细描述>根据shopId查询招牌菜
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/shop/getShopsSignByShopId", method = RequestMethod.GET)
    List<ShopSignEntity> getShopsSignByShopId(@RequestBody HomeReqEntity entity);

    /**
     * 
     * <功能详细描述>根据code查询探店
     * @param code
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/shop/getInterviewByCode", method = RequestMethod.GET)
    List<InterviewParentEntity> getInterviewByCode(@RequestBody HomeReqEntity entity);

    /**
     * 
     * <功能详细描述>根据shopid查询商户信息
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/shop/getShopsInfoByShopId", method = RequestMethod.GET)
    ShopInfoEntity getShopsInfoByShopId(@RequestBody HomeReqEntity entity);

    /**
     * 
     * <功能详细描述>根据Code查询商户信息
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/shop/getShopsInfoByCode", method = RequestMethod.GET)
    ShopInfoEntity getShopsInfoByCode(@RequestBody HomeReqEntity entity);

    /**
     * 
     * <功能详细描述>根据cityid获取分类信息
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/shop/getShopsClassByCityId", method = RequestMethod.GET)
    List<ShopFirstClassEntity> getShopsClassByCityId(@RequestBody HomeReqEntity entity);

    /**
     * 
     * <功能详细描述>根据cityid查询地区
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/shop/getShopsDistrictByCityId", method = RequestMethod.GET)
    List<ShopDistrictEntity> getShopsDistrictByCityId(@RequestBody HomeReqEntity entity);

    /**
     * 
     * <功能详细描述>根据城市id查询热词
     * @param cityid
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/shop/getHotWordsBycityId", method = RequestMethod.GET)
    List<ShopHotWordEntity> getHotWordsBycityId(@RequestParam("cityId") int cityId,
            @RequestParam("trackId") String trackId);

    /**
     * 
     * <功能详细描述>保存商家报错信息
     * @param entity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/shop/saveShopsInfoError", method = RequestMethod.POST)
    int saveShopsInfoError(@RequestBody ShopInfoErrorEntity entity);

    /**
     * 
     * <功能详细描述>获取榜单banner
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/shop/getRankFather", method = RequestMethod.GET)
    public List<RankFatherBusinEntity> getRankFather(@RequestParam("cityId") Integer cityId);

    /**
     * 
     * <功能详细描述>根据所属榜单获取对应榜单列表
     * @param belongedRankId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/shop/getShopByRankid", method = RequestMethod.GET)
    public List<RankSonBusinEntity> getShopByRankid(@RequestParam("belongedRankId") Integer belongedRankId);

    /**
     * 根据cityId和firstClassId查询榜单 <功能详细描述>
     * @param cityId
     * @param firstClassId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/shop/getRankByFirstClassIdAndCityId", method = RequestMethod.GET)
    public RankFatherBusinEntity getRankByFirstClassIdAndCityId(@RequestParam("cityId") Integer cityId,
            @RequestParam("firstClassId") Integer firstClassId);

    /**
     * 
     * <功能详细描述>查询所有的品质好店的shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/shop/getShopsQualityShopId", method = RequestMethod.GET)
    List<Integer> getShopsQualityShopId(@RequestParam("trackId") String trackId);

    /**
     * 根据shopId获取所属榜单以及排名 <功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/shop/getRankByshopId", method = RequestMethod.GET)
    RankSonBusinEntity getRankByshopId(@RequestParam("shopId") Integer shopId);

    /**
     * 
     * <功能详细描述>根据interviewId查询探店
     * @param interviewId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/shop/getInterviewByInterviewId", method = RequestMethod.GET)
    InterviewParentEntity getInterviewByInterviewId(@RequestParam("interviewId") int interviewId,
            @RequestParam("trackId") String trackId);

    /**
     * 
     * <功能详细描述>根据shopId获取当前翻倍日
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/shop/getShopsNowEventsByShopId", method = RequestMethod.GET)
    ShopEventEntity getShopsNowEventsByShopId(@RequestParam("shopId") int shopId,
            @RequestParam("trackId") String trackId);

    /**
     * 
     * <功能详细描述>根据shopId和event取消翻倍日
     * @param entity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/shop/updateShopEventStatus", method = RequestMethod.GET)
    boolean updateShopEventStatus(@RequestBody ShopEventEntity entity);

    /**
     * 
     * <功能详细描述>查询新店数量
     * @param cityId
     * @param firstClassId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/shop/getNewShopNum", method = RequestMethod.GET)
    int getNewShopNum(@RequestBody HomeReqEntity entity);
}
