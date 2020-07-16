package com.offcn.sellergoods.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.offcn.entity.PageResult;
import com.offcn.entity.Result;
import com.offcn.pojo.TbBrand;
import com.offcn.sellergoods.service.BrandService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {



    @Reference(timeout = 50000)
    private BrandService brandService;

    //查询所有商品
    @RequestMapping("findAll")
    public List<TbBrand> findAll(){


        return brandService.findAll();
    }

    //查询分页
    @RequestMapping("/findPage")
    public PageResult  findPage(int page,int rows){
        return brandService.findPage(page, rows);
    }

    //增
    @RequestMapping("/add")
    public Result add(@RequestBody TbBrand brand){
        try {

            brandService.add(brand);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    //改
    @RequestMapping("/update")
    public Result update(@RequestBody TbBrand brand){
        try {
            brandService.update(brand);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }


    /**
     * 获取实体
     * @param id
     * @return
     */
    //反显
    @RequestMapping("/findOne")
    public TbBrand findOne(Long id){
        return brandService.findOne(id);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("/delect")
    public Result delect(long[] ids){
        try {
            brandService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    //模糊查询
    @RequestMapping("/search")
    public PageResult search(@RequestBody TbBrand brand, int page, int rows  ){

        PageResult pageResult = brandService.findPage(brand, page, rows);

        return pageResult;
    }






}
