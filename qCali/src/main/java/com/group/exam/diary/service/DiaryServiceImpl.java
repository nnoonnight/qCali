package com.group.exam.diary.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.group.exam.diary.command.DiaryListCommand;
import com.group.exam.diary.dao.DiaryDao;
import com.group.exam.diary.vo.DiaryHeartVo;
import com.group.exam.diary.vo.DiaryVo;
import com.group.exam.utils.Criteria;
@Service
public class DiaryServiceImpl implements DiaryService {

	
	@Autowired
	private DiaryDao diaryDao;
	
	public DiaryServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void insertDiary(DiaryVo diaryVo) {
		diaryDao.insertDiary(diaryVo);

	}

	
	@Override
	public void updateDiary(String diaryTitle, String diaryContent, Long diarySeq, String diaryOpen, String diaryImg) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("diaryTitle", diaryTitle);
		map.put("diaryContent", diaryContent);
		map.put("diarySeq", diarySeq);
		map.put("diaryOpen", diaryOpen);
		map.put("diaryImg", diaryImg);
		diaryDao.updateDiary(map);
		
		
	}


	@Override
	public void deleteDiary(Long diarySeq, Long memberSeq) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("diarySeq", diarySeq);
		map.put("memberSeq", memberSeq);
		diaryDao.deleteDiary(map);
		
		
	}

	@Override
	public void deleteDiaryImg (Long diarySeq) {
		diaryDao.deleteDiaryImg(diarySeq);
	}
	
	@Override
	public List<DiaryListCommand> diaryList(Criteria cri, Long memberSeq){
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("memberSeq", memberSeq);
		map.put("rowStart", cri.getRowStart());
		map.put("rowEnd", cri.getRowEnd());
		return diaryDao.diaryList(map);
	}

	@Override
	public DiaryListCommand diaryDetail(Long diarySeq) {
		return diaryDao.diaryDetail(diarySeq);
	}

	@Override
	public void diaryCountup(Long diarySeq) {
		diaryDao.diaryCountup(diarySeq);

	}
	
	@Override
	public String memberAuth(Long memberSeq) {
		// TODO Auto-generated method stub
		return diaryDao.memberAuth(memberSeq);
	}
	
	@Override
	public String diaryNickname (Long memberSeq) {
		return diaryDao.diaryNickname(memberSeq);
	}

	@Override
	public int getDiaryLike(DiaryHeartVo vo) {
		return diaryDao.getDiaryLike(vo);
	}

	@Override
	public void insertDiaryLike(DiaryHeartVo vo) {
		diaryDao.insertDiaryLike(vo);
		diaryDao.updateDiaryLike(vo.getDiarySeq());
		

	}

	@Override
	public void deleteDiaryLike(DiaryHeartVo vo) {
		diaryDao.deleteDiaryLike(vo);
		diaryDao.updateDiaryLike(vo.getDiarySeq());

	}

	@Override
	public int diaryListCount(Long memberSeq) {
		return diaryDao.diaryListCount(memberSeq);
	}


	@Override
	public String upload(String category, MultipartFile file, HttpSession session) {
		String resources = "C:/qCali/resources";
		String upload = resources + "/upload";
		String folder = upload + "/" + category + "/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		
		File f = new File(folder);
		if(!f.exists()) {f.mkdirs();}
		String uuid = UUID.randomUUID().toString()+"_"+file.getOriginalFilename();
		try {
			file.transferTo(new File(folder, uuid));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return folder.substring(resources.length())+"/"+uuid;
	}

	





}
