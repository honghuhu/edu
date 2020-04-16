package org.online.edu.service.impl;

import org.online.edu.entity.Comment;
import org.online.edu.mapper.CommentMapper;
import org.online.edu.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author 007
 * @since 2020-04-16
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
