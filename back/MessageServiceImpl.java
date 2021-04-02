package com.example.service.impl;

import com.example.entity.Message;
import com.example.mapper.MessageMapper;
import com.example.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author directorfan
 * @since 2020-07-31
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

}
