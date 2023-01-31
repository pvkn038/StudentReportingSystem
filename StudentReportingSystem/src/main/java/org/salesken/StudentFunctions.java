package org.salesken;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.salesken.Semester1;

public class StudentFunctions 
{
	private static Scanner scanner;
	private static Session session;
	Student student;
	
	static
	{
		scanner = new Scanner(System.in);
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		session = sessionFactory.openSession();
	}
	
	public void AddStudent(int SemNo,String RollNo, String Name,int EnglishMark,int MathMark,int ScienceMark)
	{
		if(SemNo == 1)
		{
			Semester1  sem1= new Semester1();
			sem1.setName(Name);
			sem1.setRollNo(RollNo);
			SetMarks1(sem1,EnglishMark,MathMark,ScienceMark);
			student = sem1;
		}
		else if(SemNo == 2)
		{
			Semester2 sem2 = new Semester2();
			SetMarks2(sem2,EnglishMark,MathMark,ScienceMark);
			student = sem2;
		}
		else
		{
			System.err.println("Invalid Sem");
			return;
		}
		try
		{
			Transaction transaction = session.beginTransaction();
			session.save(student);
			transaction.commit();
		}
		catch(Exception e)
		{
			System.out.println("Student details already exist in the db");
			return;
		}
	}
	public void SetMarks1(Semester1 sem1,int EnglishMark,int MathMark,int ScienceMark)
	{
		if(EnglishMark >= 0 && EnglishMark <= 100)
		{
			sem1.setEnglishMarks(EnglishMark);
		}
		else
		{
			System.err.println("Invalid Mark");
			return;
		}
		if(MathMark >= 0 && MathMark <= 100)
		{
			sem1.setMathsMarks(MathMark);
		}
		else
		{
			System.err.println("Invalid Mark");
			return;
		}
		if(ScienceMark >= 0 && ScienceMark <= 100)
		{
			sem1.setScienceMarks(ScienceMark);
		}
		else
		{
			System.err.println("Invalid Mark");
			return;
		}
	}
	public void SetMarks2(Semester2 sem2,int EnglishMark,int MathMark,int ScienceMark)
	{
		if(EnglishMark >= 0 && EnglishMark <= 100)
		{
			sem2.setEnglishMarks(EnglishMark);
		}
		else
		{
			System.err.println("Invalid Mark");
			return;
		}
		if(MathMark >= 0 && MathMark <= 100)
		{
			sem2.setMathsMarks(MathMark);
		}
		else
		{
			System.err.println("Invalid Mark");
			return;
		}
		if(ScienceMark >= 0 && ScienceMark <= 100)
		{
			sem2.setScienceMarks(ScienceMark);
		}
		else
		{
			System.err.println("Invalid Mark");
			return;
		}
	}
	
	public void AddMark(String RollNo,int semNo,int EnglishMark,int MathMark,int ScienceMark)
	{
		try
		{
			if(semNo == 1)
			{
				Semester1 sem1 = (Semester1)session.get(Semester1.class, RollNo);
				//Transaction transaction = session.beginTransaction();
				SetMarks1(sem1,EnglishMark,MathMark,ScienceMark);
			}
			else if(semNo == 2)
			{
				Semester2 sem2 = (Semester2)session.get(Semester2.class, RollNo);
				SetMarks2(sem2,EnglishMark,MathMark,ScienceMark);
			}
		}
		catch(Exception e)
		{
			System.err.println("Not Found in the database");
			return;
		}
		System.out.println("Successfully Marks added");
		
	}
	public void AveragePercentage()
	{
		int sum = 0;
		int count = 0;
		Query query = session.createQuery("from Semester2");
		List list = query.list();
		for(Object object : list)
		{
			Semester2 sem2 = (Semester2)object;
			int marks = sem2.getEnglishMarks() + sem2.getMathsMarks() + sem2.getScienceMarks();
			sum = sum + marks / 3;
			count++;
		}
		int result = sum / count;
		System.out.println("Average Percentge" + result);
	}
	public void AverageMarksInASubject(int SemNo,int subNo)
	{
		int sum = 0;
		int countSubject = 0;
		if(SemNo == 1)
		{
			if(subNo == 1)//1 for English 2 for Maths 3 for science
			{
				Query query = session.createQuery("from Semester1");
				List list = query.list();
				for(Object obj : list)
				{
					Semester1 sem1 = (Semester1)obj;
					sum = sum + sem1.getEnglishMarks();
					countSubject++;
				}
			}
			else if(subNo == 2)//1 for English 2 for Maths 3 for science
			{
				Query query = session.createQuery("from Semester1");
				List list = query.list();
				for(Object obj : list)
				{
					Semester1 sem1 = (Semester1)obj;
					sum = sum + sem1.getMathsMarks();
					countSubject++;
				}
			}
			else if(subNo == 3)//1 for English 2 for Maths 3 for science
			{
				Query query = session.createQuery("from Semester1");
				List list = query.list();
				for(Object obj : list)
				{
					Semester1 sem1 = (Semester1)obj;
					sum = sum + sem1.getScienceMarks();
					countSubject++;
				}
			}
			else
			{
				System.out.println("Invalid Subject");
			}
		}
		if(SemNo == 2)
		{
			if(subNo == 1)//1 for English 2 for Maths 3 for science
			{
				Query query = session.createQuery("from Semester2");
				List list = query.list();
				for(Object obj : list)
				{
					Semester2 sem2 = (Semester2)obj;
					sum = sum + sem2.getEnglishMarks();
					countSubject++;
				}
			}
			else if(subNo == 2)//1 for English 2 for Maths 3 for science
			{
				Query query = session.createQuery("from Semester2");
				List list = query.list();
				for(Object obj : list)
				{
					Semester2 sem2 = (Semester2)obj;
					sum = sum + sem2.getMathsMarks();
					countSubject++;
				}
			}
			else if(subNo == 3)//1 for English 2 for Maths 3 for science
			{
				Query query = session.createQuery("from Semester2");
				List list = query.list();
				for(Object obj : list)
				{
					Semester2 sem2 = (Semester2)obj;
					sum = sum + sem2.getScienceMarks();
					countSubject++;
				}
			}
			else
			{
				System.out.println("Invalid Subject");
			}
			
		}
		
	}
	public void Top2Students()
	{
		Student student1 = new Student();
		Student student2 = new Student();
		
		int firstSum = 0;
		int secondSum = 0;
		int countStudent = 0;
		Query query1 = session.createQuery("from Semester1");
		List list1 = query1.list();
		for(Object object : list1)
		{
			Semester1 sem1 = (Semester1)object;
			int avg = sem1.getEnglishMarks()+sem1.getMathsMarks()+sem1.getScienceMarks())/3;
			if(avg > firstSum)
			{
				firstSum = avg;
				student1 = sem1;
			}
			if(avg < firstSum && avg > secondSum)
			{
				secondSum = avg;
				student2 = sem1;
			}
		}
		Query query = session.createQuery("from Semester2");
		List list = query.list();
		for(Object object : list)
		{
			Semester2 sem2 = (Semester2)object;
			int avg = (sem2.getEnglishMarks()+sem2.getMathsMarks()+sem2.getScienceMarks())/3;
			if(avg > firstSum)
			{
				firstSum = avg;
				student1 = sem2;
			}
			if(avg < firstSum && avg > secondSum)
			{
				secondSum = avg;
				student2 = sem2;
			}
		}
		System.out.println("Top 2 Students" + student1.toString() + student2.toString());
	}

}
