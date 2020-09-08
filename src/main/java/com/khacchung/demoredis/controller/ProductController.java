package com.khacchung.demoredis.controller;

import com.google.gson.Gson;
import com.khacchung.demoredis.DAO.ProductDetail;
import com.khacchung.demoredis.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class ProductController {
    private static final String KEY_SAVE = "productDetail";
    private Map<Integer, List<Date>> listRequest = new TreeMap<>();
    private static Logger logger = Logger.getLogger(ProductController.class);


    @Autowired
    private ProductService productService;

    @Autowired
    private RedisTemplate template;

    @RequestMapping(value = "/")
    public String viewHomePage(Model model) {
        List<ProductDetail> productDetailList = productService.getAllProduct();
        model.addAttribute("listProducts", productDetailList);
        return "index";
    }

    @RequestMapping(value = "/detail/{id}")
    public ModelAndView viewDetailProduct(@PathVariable(name = "id") int id) {
        ModelAndView modelAndView = new ModelAndView("product_detail");
        ProductDetail productDetail;
        String exist = null;
        Gson gson = new Gson();
        try {
            exist = (String) template.opsForValue().get(KEY_SAVE + id);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        boolean check = false;
        if (exist != null) {
            productDetail = gson.fromJson(exist, ProductDetail.class);
            check = true;
        } else {
            productDetail = productService.getById(id);
        }
        if (!check) {
            List<Date> dateList = listRequest.get(id);
            if (dateList == null) {
                dateList = new ArrayList<>();
                dateList.add(0, new Date());
            } else if (dateList.size() >= 4) {
                dateList.add(0, new Date());
                long tmp = dateList.get(0).getTime() - dateList.get(4).getTime();
                if (tmp <= 300000) {
                    String json = gson.toJson(productDetail);
                    try {
                        template.opsForValue().set(KEY_SAVE + id, json);
                        logger.info("Save Product success");
                    }catch (Exception e){
                        logger.error(e.getMessage());
                    }
                }
            } else {
                dateList.add(0, new Date());
            }

            listRequest.put(id, dateList);
        }

        modelAndView.addObject("productDetail", productDetail);
        if (check) {
            modelAndView.addObject("typeGet", "Lấy dữ liệu từ Cache");
        } else {
            modelAndView.addObject("typeGet", "Lấy dữ liệu từ Database");
        }
        return modelAndView;
    }
}
