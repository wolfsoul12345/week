package com.dream.week.entity;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {
    private Long totalData;// 总条数
    private List<T> items;// 当前页数据

    public PageResult() {
    }

    public PageResult(Long total, List<T> items) {
        this.totalData = total;
        this.items = items;
    }


    /** demo
     *  public PageResult<Spu> querySpuByPage(Integer page, Integer rows, Boolean sortBy, String key) {
     *         //分页
     *         PageHelper.startPage(page,rows);
     *         //过滤
     *         Example example = new Example(Spu.class);
     *         //搜索字段过滤
     *         Example.Criteria criteria = example.createCriteria();
     *         if(StringUtils.isNotBlank(key)) criteria.andLike("title","%"+key+"%");
     *         if (sortBy!=null) criteria.andEqualTo("saleable",sortBy);
     *         //默认排序
     *         example.setOrderByClause("last_update_time DESC ");
     *         List<Spu> list = spuMapper.selectByExample(example);
     *         if (CollectionUtils.isEmpty(list)) throw new LyException(ExceptionEnums.GOODS_NOT_FOUND);
     *         //解析分类和品牌的名称
     *         LoadCategoryAndBrandName(list);
     *         //解析分页结果
     *         PageInfo<Spu> spuPageInfo = new PageInfo<>(list);
     *         return new PageResult<>(spuPageInfo.getTotal(),list);
     *     }
     */

}