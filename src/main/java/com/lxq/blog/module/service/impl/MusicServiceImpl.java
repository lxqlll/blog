package com.lxq.blog.module.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxq.blog.module.mapper.MusicMapper;
import com.lxq.blog.module.pojo.Music;
import com.lxq.blog.module.service.MusicService;
import com.lxq.blog.utils.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


/**
 * <p>
 * </p>
 *
 * @author lxq
 * @date 2020年5月12日15:34:08
 * @Version 1.0
 */
@Service
@Slf4j
public class MusicServiceImpl implements MusicService {

    /**
     * 声明MusicMapper对象
     */
    @Autowired
    private MusicMapper musicMapper;

    @Override
    public boolean saveOrUpdate(Music music) {
        boolean falg = false;
        Music m = this.queryById(music.getId());
        if(null==m){
            if (musicMapper.insert(music)>0){
                falg = true;
            }
        }else {
            if (musicMapper.updateById(music)>0){
                falg = true;
            }
        }
        return falg;
    }

    @Override
    public Music queryById(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("deleted",0);
        queryWrapper.eq("id",id);
        Music music = musicMapper.selectOne(queryWrapper);
        return music;
    }

    @Override
    public boolean deleteById(Integer id) {
        Music music = this.queryById(id);
        Music m = new Music();
        m.setId(music.getId());
        m.setDeleted(1);
        if (this.saveOrUpdate(m)){
            return true;
        }
        return false;
    }

    @Override
    public void start(Integer id) {
        Music music = this.queryById(id);
        music.setEnabled(1);
        this.saveOrUpdate(music);
    }

    @Override
    public void forbidden(Integer id) {
        Music music = this.queryById(id);
        music.setEnabled(0);
        this.saveOrUpdate(music);
    }

    @Override
    public Page getList(Page page) {
        List<Music> musics = musicMapper.getList(page);
        page.setList(musics);
        page.setTotalCount(musicMapper.getCountByPage(page));
        return page;
    }

    @Override
    public List<Music> showList() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("deleted",0);
        queryWrapper.eq("enable",1);
        List<Music> musics = musicMapper.selectList(queryWrapper);
        return musics;
    }
}
