package com.offcn.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.offcn.entity.PageResult;
import com.offcn.mapper.TbBrandMapper;
import com.offcn.pojo.TbBrand;
import com.offcn.pojo.TbBrandExample;
import com.offcn.sellergoods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private TbBrandMapper tbBrandMapper;


    public List<TbBrand> findAll() {
        return tbBrandMapper.selectByExample(null);
    }


        public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbBrand> page=   (Page<TbBrand>) tbBrandMapper.selectByExample(null);
        //使用PageInfo也可以
        // PageInfo<TbBrand> pageInfo = new PageInfo<>(brandMapper.selectByExample(null));
        //    return new PageResult(pageInfo.getTotal(),pageInfo.getList());
        return new PageResult(page.getTotal(), page.getResult());
    }

    public void add(TbBrand brand) {
        tbBrandMapper.insert(brand);
    }

    /**
     * 修改
     */
    public void update(TbBrand brand) {
        tbBrandMapper.updateByPrimaryKey(brand);
    }
    /**
     * 根据ID获取实体
     * @param id
     * @return
     */
    public TbBrand findOne(Long id) {
        return tbBrandMapper.selectByPrimaryKey(id);
    }

    public void delete(long[] ids) {
        for (long id:ids
             ) {
            tbBrandMapper.deleteByPrimaryKey(id);
        }
    }

    public PageResult findPage(TbBrand brand, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        TbBrandExample example=new TbBrandExample();
        TbBrandExample.Criteria criteria = example.createCriteria();
        if(brand!=null){
            if(brand.getName()!=null && brand.getName().length()>0){
                criteria.andNameLike("%"+brand.getName()+"%");
            }
            if(brand.getFirstChar()!=null && brand.getFirstChar().length()>0){
                criteria.andFirstCharEqualTo(brand.getFirstChar());
            }
        }
        Page<TbBrand> page= (Page<TbBrand>)tbBrandMapper.selectByExample(example);

        PageResult pageResult = new PageResult(page.getTotal(), page.getResult());

        return pageResult;


    }
}
