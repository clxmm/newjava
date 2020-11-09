package org.clxmm.service.edu.service.impl;

import org.clxmm.service.edu.entity.Comment;
import org.clxmm.service.edu.mapper.CommentMapper;
import org.clxmm.service.edu.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author clxmm
 * @since 2020-11-04
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
