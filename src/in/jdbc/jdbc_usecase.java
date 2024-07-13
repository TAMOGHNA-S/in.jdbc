package in.jdbc;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;
public class jdbc_usecase 
{
		public static void main(String[] args)throws Exception
		{
			Scanner sc=new Scanner(System.in);
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_db","root", "root");
			//Statement st=con.createStatement();
			//String researchers="CREATE TABLE RESEARCHER"+"(researcher_id VARCHAR(20), "+"researcher_name VARCHAR(50),"+"specialization  VARCHAR(50),"+"email VARCHAR(50),"+"phone_number VARCHAR(20),"+"PRIMARY KEY (researcher_id))";
			//st.executeUpdate(researchers);
			//String experiment="CREATE TABLE EXPERIMENT"+"(experiment_id VARCHAR(20), "+"experiment_name VARCHAR(50),"+"researcher_id  VARCHAR(20),"+"start_date DATE,"+"end_date DATE,"+"status VARCHAR(20),"+"PRIMARY KEY (experiment_id),"+"FOREIGN KEY (researcher_id) REFERENCES RESEARCHER(researcher_id),"+"CHECK (status='Planned' OR status='In Progress' OR status='Completed'))";
			//st.executeUpdate(experiment);
			//String sample="CREATE TABLE SAMPLE"+"(sample_id VARCHAR(20), "+"sample_name VARCHAR(50),"+"experiment_id  VARCHAR(20),"+"collection_date DATE,"+"description VARCHAR(250),"+"PRIMARY KEY (sample_id),"+"FOREIGN KEY (experiment_id) REFERENCES EXPERIMENT(experiment_id))";
			//st.executeUpdate(sample);
			int n=1;
			while(n!=0)
			{
				System.out.println("Press A to manage experiments\nPress B to manage samples\nPress C to manage researcher\nPress D to exit\n\nEnter your choice:");
				char ch=sc.next().charAt(0);
				switch(ch)
				{
					case 'A':System.out.println("Press A to Add new experiment\nPress B to View experiment details\nPress C to Update experiment information\nPress D to Delete experiments\n");
							char c=sc.next().charAt(0);
							switch(c)
							{
								case 'A':System.out.println("Enter the Experiment id, Researcher id, Experiment name, Experiment Start Date, Experiment end date, Experiment Status in order: ");
										sc.nextLine();
									   String eid=sc.nextLine();
									   String rid=sc.nextLine();
									   String ename=sc.nextLine();
									   String esd=sc.nextLine();
									   Date utilDate1=new SimpleDateFormat("yyyy-MM-dd").parse(esd);
									   java.sql.Date sqlDate1=new java.sql.Date(utilDate1.getTime());
									   String eed=sc.nextLine();
									   Date utilDate2=new SimpleDateFormat("yyyy-MM-dd").parse(eed);
									   java.sql.Date sqlDate2=new java.sql.Date(utilDate2.getTime());
									   String es=sc.nextLine();
									   PreparedStatement ps=con.prepareStatement("insert into experiment values('"+eid+"','"+ename+"','"+rid+"',?,?,'"+es+"')");
									   ps.setDate(1, sqlDate1);
									   ps.setDate(2, sqlDate2);
									   int j=ps.executeUpdate();
									   if(j>0)
										   System.out.println("Experiment added successfully");
									   else
										   System.out.println("Adding experiment failed");
									   break;
								case 'B':PreparedStatement pst=con.prepareStatement("Select * from experiment");
									   ResultSet rs2=pst.executeQuery();
									   while(rs2.next())
									   {
										   String eidd=rs2.getString("experiment_id");
										   System.out.println("Experiment id: "+eidd);
										   String enamee=rs2.getString("experiment_name");
										   System.out.println("Experiment name: "+enamee);
										   String ridd=rs2.getString("researcher_id");
										   System.out.println("Researcher id: "+ridd);
										   Date esdd=rs2.getDate("start_date");
										   System.out.println("Start date: "+esdd);
										   Date eedd=rs2.getDate("end_date");
										   System.out.println("End date: "+eedd);
										   String ess=rs2.getString("status");
										   System.out.println("Status: "+ess);
										   System.out.println("--------------------------------------------------------");
									   }
									   break;
								case 'C':System.out.println("Enter the Experiment id for which the Experiment status is to be changed and the new status in order:");
										sc.nextLine();
									   String id=sc.nextLine();
									   String newstatus=sc.nextLine();
									   PreparedStatement pstmt=con. prepareStatement("update experiment set status=? where experiment_id=?");
									   pstmt.setString(1, newstatus);
									   pstmt.setString(2,id);
									   int k=pstmt.executeUpdate();
									   if(k>0)
										   System.out.println("Updation successful");
									   else
										   System.out.println("Updation failed");
									   break;
								case 'D':System.out.println("Enter the Experiment name which is to be deleted:");
										 sc.nextLine();
										 String del=sc.nextLine();
										 try 
										 {
											 PreparedStatement p=con.prepareStatement("delete from experiment where experiment_name=?");
											 p.setString(1, del);
											 int l=p.executeUpdate();
											 if(l>0)
												 System.out.println("Deletion successful");
											 else
												 System.out.println("Deletion failed");
										 }
										 catch(Exception e)
										 {
											 System.out.println("Deletion failed");
										 }
										break;
								default:System.out.println("Invalid choice! ");
								}
							break;
					case 'B':System.out.println("Press A to Add new samples\nPress B to View sample details\nPress C to Update sample information\nPress D to Delete samples\n\nEnter your choice:");
						   char choice=sc.next().charAt(0);
						   switch(choice)
						   {
						   		case 'A':System.out.println("Enter the Sample id, Experiment id, Sample name, Collection Date and Sample description in order:");
						   				sc.nextLine();
								       String sid=sc.nextLine();
								       String eid=sc.nextLine();
								       String sname=sc.nextLine();
								       String cd=sc.nextLine();
								       Date utilDate=new SimpleDateFormat("yyyy-MM-dd").parse(cd);
									   java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());
								       String sd=sc.nextLine();
								       PreparedStatement ps=con.prepareStatement("insert into sample values('"+sid+"','"+sname+"','"+eid+"',?,'"+sd+"')");
								       ps.setDate(1, sqlDate);
								       int i=ps.executeUpdate();
								       if(i>0)
								    	   System.out.println("Sample added successfully");
								       else
								    	   System.out.println("Adding sample failed");
								       break;
								case 'B':PreparedStatement pst=con.prepareStatement("Select * from sample");
									   ResultSet rs2=pst.executeQuery();
									   while(rs2.next())
									   {
										   String sidd=rs2.getString("sample_id");
										   System.out.println("Sample Id: "+sidd);
										   String snamee=rs2.getString("sample_name");
										   System.out.println("Sample name: "+snamee);
										   String eidd=rs2.getString("experiment_id");
										   System.out.println("Experiment Id: "+eidd);
										   Date cdd=rs2.getDate("collection_date");
										   System.out.println("Collection Date: "+cdd);
										   String sdd=rs2.getString("description");
										   System.out.println("Description :"+sdd);
										   System.out.println("--------------------------------------------------------");
									   }
									   break;
								case 'C':System.out.println("Enter the Sample id for which the Sample description is to be changed and the new description in order:");
										 sc.nextLine();
										 String id=sc.nextLine();
									     String newdesc=sc.nextLine();
									     PreparedStatement pstmt=con. prepareStatement("update sample set description=? where sample_id=?");
									     pstmt.setString(1, newdesc);
									     pstmt.setString(2,id);
									     int k=pstmt.executeUpdate();
									     if(k>0)
										     System.out.println("Updation successful");
									     else
										     System.out.println("Updation failed");
									     break;
								case 'D':System.out.println("Enter the Sample name which is to be deleted:");
								 		 sc.nextLine();
								 		 String del=sc.nextLine();
								 		 try 
								 		 {
								 			 PreparedStatement p=con.prepareStatement("delete from sample where sample_name=?");
								 			 p.setString(1, del);
								 			 int l=p.executeUpdate();
								 			 if(l>0)
								 				 System.out.println("Deletion successful");
								 			 else
								 				 System.out.println("Deletion failed");
								 		 }
								 		 catch(Exception e)
								 		 {
								 			 System.out.println("Deletion failed");
								 		 }
								 		 break;
								default:System.out.println("Invalid choice! ");
						   }
						   break;
					case 'C':System.out.println("Press A to Add new Researcher\nPress B to View Researcher details\nPress C to Update Researcher information\nPress D to Delete Researchers\n\nEnter your choice:");
						   char cho=sc.next().charAt(0);
						   switch(cho)
						   {
						   		case 'A':System.out.println("Enter the Researcher id, Researcher name, Specialization, Researcher email and Researcher phone number in order: ");
						   				sc.nextLine();
								       String rid=sc.nextLine();
								       String rname=sc.nextLine();
								       String rs=sc.nextLine();
								       String re=sc.nextLine();
								       String rpn=sc.nextLine();
								       PreparedStatement ps=con.prepareStatement("insert into researcher values('"+rid+"','"+rname+"','"+rs+"','"+re+"','"+rpn+"')");
								       int i=ps.executeUpdate();
								       if(i>0)
									       System.out.println("Researcher added successfully");
								       else
									       System.out.println("Adding researcher failed");
								       break;
								case 'B':PreparedStatement pst=con.prepareStatement("Select * from researcher");
									   ResultSet rs2=pst.executeQuery();
									   while(rs2.next())
									   {
										   String ridd=rs2.getString("researcher_id");
										   System.out.println("Researcher id: "+ridd);
										   String rnamee=rs2.getString("researcher_name");
										   System.out.println("Researcher name: "+rnamee);
										   String rss=rs2.getString("specialization");
										   System.out.println("Specialization: "+rss);
										   String ree=rs2.getString("email");
										   System.out.println("Email: "+ree);
										   String eedd=rs2.getString("phone_number");
										   System.out.println("Phone number :"+eedd);
										   System.out.println("--------------------------------------------------------");
									   }
									   break;
								case 'C':System.out.println("Enter the Researcher id for which the Specialization is to be changed and the new Specialization in order:");
										 sc.nextLine();
									     String id=sc.nextLine();
									     String newspec=sc.nextLine();
									     PreparedStatement pstmt=con. prepareStatement("update researcher set specialization=? where researcher_id=?");
									     pstmt.setString(1, newspec);
									     pstmt.setString(2,id);
									     int k=pstmt.executeUpdate();
									     if(k>0)
										     System.out.println("Updation successful");
									     else
										     System.out.println("Updation failed");
									     break;
								case 'D':System.out.println("Enter the Researcher name which is to be deleted:");
								 		 sc.nextLine();
								 		 String del=sc.nextLine();
								 		 try 
								 		 {
								 			 PreparedStatement p=con.prepareStatement("delete from researcher where researcher_name=?");
								 			 p.setString(1, del);
								 			 int l=p.executeUpdate();
								 			 if(l>0)
								 				 System.out.println("Deletion successful");
								 			 else
								 				 System.out.println("Deletion failed");
								 		 }
								 		 catch(Exception e)
								 		 {
								 			 System.out.println("Deletion failed");
								 		 }
								 		 break;
								default:System.out.println("Invalid choice! ");
						   	}
						   break;
					case 'D':System.out.println("Thank You !!!");
						   n=0;
						   break;
					default:System.out.println("Invalid Choice !\nPlease enter correct input");
				}	
			}
			con.close();
		}
}
