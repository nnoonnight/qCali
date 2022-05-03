package com.group.exam.qna.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.group.exam.qna.dao.QnaDao;
import com.group.exam.qna.vo.QnaVo;
import com.group.exam.utils.Criteria;

@Service
public class QnaServiceImpl implements QnaService{
	
	private QnaDao qnaDao;
	
	@Autowired
	public QnaServiceImpl(QnaDao qnaDao) {
		this.qnaDao = qnaDao;
	}
	
	
	@Override
	public List<QnaVo> list(Criteria cri) {
		// TODO Auto-generated method stub
		return qnaDao.list(cri);
	}

	@Override
	public void write(QnaVo vo){
		qnaDao.qnaInsert(vo);
	}


	@Override
	public QnaVo detail(Long qnaSeq) {
		// TODO Auto-generated method stub
		return qnaDao.detail(qnaSeq);
	}


	@Override
	public void qnaUpdate(QnaVo vo) {
		// TODO Auto-generated method stub
		qnaDao.qnaUpdate(vo);
	}


	@Override
	public void qnaDelete(Long qnaSeq) {
		// TODO Auto-generated method stub
		qnaDao.qnaDelete(qnaSeq);
	}


	@Override
	public void qnaRead(Long qnaSeq) {
		// TODO Auto-generated method stub
		qnaDao.qnaRead(qnaSeq);
	}


	@Override
	public void qnaReplyInsert(QnaVo vo) {
		// TODO Auto-generated method stub
		qnaDao.qnaReplyInsert(vo);
	}

	//파일 다운로드
	@Override
	public File download(String filename, String filepath, HttpSession session, HttpServletResponse response) {
		
		
		File file = new File("C:\\project\\workspace\\resources\\"+filepath);
		String mime = session.getServletContext().getMimeType(filename);
		System.out.println(file);
		response.setContentType(mime);
		
		try {
																	//+는 기호라 \필요, \또한 기호라 \필요
																	//%20 = 스페이스바
			filename = URLEncoder.encode(filename, "utf-8").replaceAll("\\+", "%20");
			response.setHeader("content-disposition", "attachment; filename="+filename);
			
			System.out.println(filename);
			ServletOutputStream out = response.getOutputStream();
			FileCopyUtils.copy(new FileInputStream(file), out);
			out.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		
		return file;
	}
	
	//파일 업로드
	@Override
	public String upload(String category, MultipartFile file, HttpSession session) {
		//서버의 업로드할 물리적 위치
		String resources = "C:/project/workspace/resources";
		String upload = resources + "/upload";
		String folder = upload + "/" + category + "/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		
		File f = new File(folder);
		if(!f.exists()) {f.mkdirs(); }
		String uuid = UUID.randomUUID().toString()+"_"+file.getOriginalFilename();
		try {
			file.transferTo(new File(folder, uuid));
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return folder.substring(resources.length()) + "/" + uuid;
	}


	@Override
	public int qnaTotalList() {
		// TODO Auto-generated method stub
		return qnaDao.qnaTotalList();
	}
}
