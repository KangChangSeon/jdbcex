package com.ssg.jdbcex.todo.service;

import com.ssg.jdbcex.todo.dao.MemberDAO;
import com.ssg.jdbcex.todo.domain.MemberVO;
import com.ssg.jdbcex.todo.dto.MemberDTO;
import com.ssg.jdbcex.todo.util.MapperUtil;
import org.modelmapper.ModelMapper;

public enum MemberService {
    INSTANCE;

    private MemberDAO memberDAO;
    private ModelMapper modelMapper;

    MemberService() {
        memberDAO = new MemberDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    public MemberDTO login(String mid, String mpw) throws Exception {
        MemberVO vo = memberDAO.getWithPassword(mid,mpw);
        MemberDTO dto = modelMapper.map(vo, MemberDTO.class);

        return dto;
    }

    public void updateUUID(String mid, String uuid) throws Exception {
        memberDAO.updateUUID(mid,uuid);
    }

    public MemberDTO getByUUID(String uuid) throws Exception {
        MemberVO vo = memberDAO.selectUUID(uuid);
        MemberDTO dto = modelMapper.map(vo, MemberDTO.class);
        return dto;
    }
}
