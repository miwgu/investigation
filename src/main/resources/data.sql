DROP TABLE IF EXISTS answer;
DROP TABLE IF EXISTS patient;
DROP TABLE IF EXISTS answer_option;
DROP TABLE IF EXISTS question;
DROP TABLE IF EXISTS survey;

-- I need change Insert Psoriasis

INSERT INTO patient (social_number, full_name, email) VALUES ('195001011234','Akiko Yamada', 'sample1@hotmail.com');
INSERT INTO patient (social_number, full_name, email) VALUES ('1945022022345','Yasuo Yoshida', 'sample2@hotmail.com');
INSERT INTO patient (social_number, full_name, email) VALUES ('198003213456','Mitsuhide Akechi', 'sample3@hotmail.com');
INSERT INTO patient (social_number, full_name, email) VALUES ('198507124567','Yumiko Nitta', 'sample4@hotmail.com');
INSERT INTO patient (social_number, full_name, email) VALUES ('199204015678','Kotomi Kasai', 'sample5@hotmail.com');
INSERT INTO patient (social_number, full_name, email) VALUES ('199908086789','Yorito Hattori', 'sample6@hotmail.com');
INSERT INTO patient (social_number, full_name, email) VALUES ('198709237890','Kiyoko Yamane', 'sample7@hotmail.com');
INSERT INTO patient (social_number, full_name, email) VALUES ('197903108901','Chiyo Toyota', 'sample8@hotmail.com');
INSERT INTO patient (social_number, full_name, email) VALUES ('196611229012','Makiko Hirata', 'sample9@hotmail.com');
INSERT INTO patient (social_number, full_name, email) VALUES ('197710080123','Hiro Terada', 'sample10@hotmail.com');
INSERT INTO patient (social_number, full_name, email) VALUES ('194612201234','Mitsuharu Honda', 'sample11@hotmail.com');
INSERT INTO patient (social_number, full_name, email) VALUES ('195705202345','Mia Persson', 'sample12@hotmail.com');

--1 Psoriasis 2 Cancer 3 Diabetes
INSERT INTO survey (name) VALUES ('Psoriasis');
INSERT INTO survey (name) VALUES ('Cancer');
INSERT INTO survey (name) VALUES ('Diabetes');

---question for Psoriasis---1-7
INSERT INTO question (num,text,survey_id) VALUES ('1','What is your gender?',1);
INSERT INTO question (num,text,survey_id) VALUES ('2','Where is your care-region?',1);
INSERT INTO question (num,text,survey_id) VALUES ('3','Are you receiving adequate treatment for psoriasis?',1);
INSERT INTO question (num,text,survey_id) VALUES ('4','How many times have you visited your health center in the past 6 months?',1);
INSERT INTO question (num,text,survey_id) VALUES ('5','Who do you often meet when you visited your health center?',1);
INSERT INTO question (num,text,survey_id) VALUES ('6','Are you satisfied with the quality of the consulting at the health center?',1);
INSERT INTO question (num,text,survey_id) VALUES ('7','How do you feel your current health status after having psoriasis?',1);

---question for Cancer---8-14
INSERT INTO question (num,text,survey_id) VALUES ('1','What is your gender?',2);
INSERT INTO question (num,text,survey_id) VALUES ('2','Where is your care-region?',2);
INSERT INTO question (num,text,survey_id) VALUES ('3','Have you undergone surgery for cancer in the past?',2);
INSERT INTO question (num,text,survey_id) VALUES ('4','How many times have you visited your health center in the past 6 months?',2);
INSERT INTO question (num,text,survey_id) VALUES ('5','Who do you often meet when you visited your health center?',2);
INSERT INTO question (num,text,survey_id) VALUES ('6','Are you satisfied with the quality of the consulting at the health center?',2);
INSERT INTO question (num,text,survey_id) VALUES ('7','How do you feel your current health status after having cancer?',2);
---question for Diabetes--- 15-21
INSERT INTO question (num,text,survey_id) VALUES ('1','What is your gender?',3);
INSERT INTO question (num,text,survey_id) VALUES ('2','Where is your care-region?',3);
INSERT INTO question (num,text,survey_id) VALUES ('3','Are you receiving adequate treatment for diabetes?',3);
INSERT INTO question (num,text,survey_id) VALUES ('4','How many times have you visited your health center in the past 6 months?',3);
INSERT INTO question (num,text,survey_id) VALUES ('5','Who do you often meet when you visited your health center?',3);
INSERT INTO question (num,text,survey_id) VALUES ('6','Are you satisfied with the quality of the consulting at the health center?',3);
INSERT INTO question (num,text,survey_id) VALUES ('7','How do you feel your current health status after having diabetes?',3);


INSERT INTO answer_option(item,question_id) VALUES ('Female',1 ); --1
INSERT INTO answer_option(item,question_id) VALUES ('Male', 1);--2
INSERT INTO answer_option(item,question_id) VALUES ('Blekinge', 2);--3
INSERT INTO answer_option(item,question_id) VALUES ('Dalarna', 2);--4
INSERT INTO answer_option(item,question_id) VALUES ('Gotland', 2);--5
INSERT INTO answer_option(item,question_id) VALUES ('Stockholm',2 );--6
INSERT INTO answer_option(item,question_id) VALUES ('Värmland', 2);--7
INSERT INTO answer_option(item,question_id) VALUES ('Västerbotten', 2);--8
INSERT INTO answer_option(item,question_id) VALUES ('Örebro', 2);--9
INSERT INTO answer_option(item,question_id) VALUES ('Yes',3 );--10
INSERT INTO answer_option(item,question_id) VALUES ('No', 3);--11
INSERT INTO answer_option(item,question_id) VALUES ('0',4 );--12
INSERT INTO answer_option(item,question_id) VALUES ('1-2', 4);--13
INSERT INTO answer_option(item,question_id) VALUES ('3-5', 4);--14
INSERT INTO answer_option(item,question_id) VALUES ('more than 6', 4);--15
INSERT INTO answer_option(item,question_id) VALUES ('physician', 5);--16
INSERT INTO answer_option(item,question_id) VALUES ('nurse', 5);--17
INSERT INTO answer_option(item,question_id) VALUES ('both physician and nurse', 5);--18
INSERT INTO answer_option(item,question_id) VALUES ('other healthcare professionals', 5);--19
INSERT INTO answer_option(item,question_id) VALUES ('Satisfied', 6);--20
INSERT INTO answer_option(item,question_id) VALUES ('I do not know', 6);--21
INSERT INTO answer_option(item,question_id) VALUES ('Unsatisfied', 6);--22
INSERT INTO answer_option(item,question_id) VALUES ('Excellent', 7);--23
INSERT INTO answer_option(item,question_id) VALUES ('Good', 7);--24
INSERT INTO answer_option(item,question_id) VALUES ('fair', 7);--25
INSERT INTO answer_option(item,question_id) VALUES ('poor', 7);--26

INSERT INTO answer_option(item,question_id) VALUES ('Female',8 ); --27
INSERT INTO answer_option(item,question_id) VALUES ('Male', 8);--28
INSERT INTO answer_option(item,question_id) VALUES ('Blekinge', 9);--29
INSERT INTO answer_option(item,question_id) VALUES ('Dalarna', 9);--30
INSERT INTO answer_option(item,question_id) VALUES ('Gotland', 9);--31
INSERT INTO answer_option(item,question_id) VALUES ('Stockholm',9 );--32
INSERT INTO answer_option(item,question_id) VALUES ('Välmland', 9);--33
INSERT INTO answer_option(item,question_id) VALUES ('Västerbotten', 9);--34
INSERT INTO answer_option(item,question_id) VALUES ('Örebro', 9);--35------
INSERT INTO answer_option(item,question_id) VALUES ('Yes',10 );--36
INSERT INTO answer_option(item,question_id) VALUES ('No', 10);--37
INSERT INTO answer_option(item,question_id) VALUES ('0',11 );--38
INSERT INTO answer_option(item,question_id) VALUES ('1-2', 11);--39
INSERT INTO answer_option(item,question_id) VALUES ('3-5', 11);--40
INSERT INTO answer_option(item,question_id) VALUES ('more than 6', 11);--41
INSERT INTO answer_option(item,question_id) VALUES ('physician', 12);--42
INSERT INTO answer_option(item,question_id) VALUES ('nurse', 12);--43
INSERT INTO answer_option(item,question_id) VALUES ('both physician and nurse', 12);--44
INSERT INTO answer_option(item,question_id) VALUES ('other healthcare professionals', 12);--45
INSERT INTO answer_option(item,question_id) VALUES ('Satisfied', 13);--46
INSERT INTO answer_option(item,question_id) VALUES ('I do not know', 13);--47
INSERT INTO answer_option(item,question_id) VALUES ('Unsatisfied', 13);--48
INSERT INTO answer_option(item,question_id) VALUES ('Excellent', 14);--49
INSERT INTO answer_option(item,question_id) VALUES ('Good', 14);--50
INSERT INTO answer_option(item,question_id) VALUES ('fair', 14);--51
INSERT INTO answer_option(item,question_id) VALUES ('poor', 14);--52

INSERT INTO answer_option(item,question_id) VALUES ('Female',15 );--53
INSERT INTO answer_option(item,question_id) VALUES ('Male', 15);--54
INSERT INTO answer_option(item,question_id) VALUES ('Blekinge', 16);--29
INSERT INTO answer_option(item,question_id) VALUES ('Dalarna', 16);--30
INSERT INTO answer_option(item,question_id) VALUES ('Gotland', 16);--31
INSERT INTO answer_option(item,question_id) VALUES ('Stockholm',16);--32
INSERT INTO answer_option(item,question_id) VALUES ('Välmland', 16);--33
INSERT INTO answer_option(item,question_id) VALUES ('Västerbotten', 16);--34
INSERT INTO answer_option(item,question_id) VALUES ('Örebro', 16);--35
INSERT INTO answer_option(item,question_id) VALUES ('Yes',17 );--36
INSERT INTO answer_option(item,question_id) VALUES ('No', 17);--37
INSERT INTO answer_option(item,question_id) VALUES ('0',18);--38
INSERT INTO answer_option(item,question_id) VALUES ('1-2', 18);--39
INSERT INTO answer_option(item,question_id) VALUES ('3-5', 18);--40
INSERT INTO answer_option(item,question_id) VALUES ('more than 6', 18);--41
INSERT INTO answer_option(item,question_id) VALUES ('physician', 19);--42
INSERT INTO answer_option(item,question_id) VALUES ('nurse', 19);--43
INSERT INTO answer_option(item,question_id) VALUES ('both physician and nurse', 19);--44
INSERT INTO answer_option(item,question_id) VALUES ('other healthcare professionals', 19);--45
INSERT INTO answer_option(item,question_id) VALUES ('Satisfied', 20);--46
INSERT INTO answer_option(item,question_id) VALUES ('I do not know', 20);--47
INSERT INTO answer_option(item,question_id) VALUES ('Unsatisfied', 20);--48
INSERT INTO answer_option(item,question_id) VALUES ('Excellent', 21);--49
INSERT INTO answer_option(item,question_id) VALUES ('Good', 21);--50
INSERT INTO answer_option(item,question_id) VALUES ('fair', 21);--51
INSERT INTO answer_option(item,question_id) VALUES ('poor', 21);--52

INSERT Into answer(patient_id, answer_op_id) VALUES (1,1);
INSERT Into answer(patient_id, answer_op_id) VALUES (1,6);
INSERT Into answer(patient_id, answer_op_id) VALUES (1,10);
INSERT Into answer(patient_id, answer_op_id) VALUES (1,13);
INSERT Into answer(patient_id, answer_op_id) VALUES (1,17);
INSERT Into answer(patient_id, answer_op_id) VALUES (1,22);
INSERT Into answer(patient_id, answer_op_id) VALUES (1,25);

INSERT Into answer(patient_id, answer_op_id) VALUES (2,2);
INSERT Into answer(patient_id, answer_op_id) VALUES (2,6);
INSERT Into answer(patient_id, answer_op_id) VALUES (2,11);
INSERT Into answer(patient_id, answer_op_id) VALUES (2,14);
INSERT Into answer(patient_id, answer_op_id) VALUES (2,16);
INSERT Into answer(patient_id, answer_op_id) VALUES (2,21);
INSERT Into answer(patient_id, answer_op_id) VALUES (2,25);

INSERT Into answer(patient_id, answer_op_id) VALUES (3,2);
INSERT Into answer(patient_id, answer_op_id) VALUES (3,8);
INSERT Into answer(patient_id, answer_op_id) VALUES (3,10);
INSERT Into answer(patient_id, answer_op_id) VALUES (3,14);
INSERT Into answer(patient_id, answer_op_id) VALUES (3,18);
INSERT Into answer(patient_id, answer_op_id) VALUES (3,21);
INSERT Into answer(patient_id, answer_op_id) VALUES (3,26);

INSERT Into answer(patient_id, answer_op_id) VALUES (4,1);
INSERT Into answer(patient_id, answer_op_id) VALUES (4,9);
INSERT Into answer(patient_id, answer_op_id) VALUES (4,10);
INSERT Into answer(patient_id, answer_op_id) VALUES (4,13);
INSERT Into answer(patient_id, answer_op_id) VALUES (4,18);
INSERT Into answer(patient_id, answer_op_id) VALUES (4,20);
INSERT Into answer(patient_id, answer_op_id) VALUES (4,24);

INSERT Into answer(patient_id, answer_op_id) VALUES (5,1);
INSERT Into answer(patient_id, answer_op_id) VALUES (5,8);
INSERT Into answer(patient_id, answer_op_id) VALUES (5,10);
INSERT Into answer(patient_id, answer_op_id) VALUES (5,15);
INSERT Into answer(patient_id, answer_op_id) VALUES (5,18);
INSERT Into answer(patient_id, answer_op_id) VALUES (5,20);
INSERT Into answer(patient_id, answer_op_id) VALUES (5,26);

----------------------------------------------------------------
INSERT Into answer(patient_id, answer_op_id) VALUES (6,28);
INSERT Into answer(patient_id, answer_op_id) VALUES (6,33);
INSERT Into answer(patient_id, answer_op_id) VALUES (6,37);
INSERT Into answer(patient_id, answer_op_id) VALUES (6,39);
INSERT Into answer(patient_id, answer_op_id) VALUES (6,43);
INSERT Into answer(patient_id, answer_op_id) VALUES (6,46);
INSERT Into answer(patient_id, answer_op_id) VALUES (6,51);

INSERT Into answer(patient_id, answer_op_id) VALUES (7,27);
INSERT Into answer(patient_id, answer_op_id) VALUES (7,29);
INSERT Into answer(patient_id, answer_op_id) VALUES (7,37);
INSERT Into answer(patient_id, answer_op_id) VALUES (7,38);
INSERT Into answer(patient_id, answer_op_id) VALUES (7,43);
INSERT Into answer(patient_id, answer_op_id) VALUES (7,46);
INSERT Into answer(patient_id, answer_op_id) VALUES (7,50);

INSERT Into answer(patient_id, answer_op_id) VALUES (8,27);
INSERT Into answer(patient_id, answer_op_id) VALUES (8,35);
INSERT Into answer(patient_id, answer_op_id) VALUES (8,36);
INSERT Into answer(patient_id, answer_op_id) VALUES (8,41);
INSERT Into answer(patient_id, answer_op_id) VALUES (8,44);
INSERT Into answer(patient_id, answer_op_id) VALUES (8,47);
INSERT Into answer(patient_id, answer_op_id) VALUES (8,50);

INSERT Into answer(patient_id, answer_op_id) VALUES (9,27);
INSERT Into answer(patient_id, answer_op_id) VALUES (9,35);
INSERT Into answer(patient_id, answer_op_id) VALUES (9,36);
INSERT Into answer(patient_id, answer_op_id) VALUES (9,39);
INSERT Into answer(patient_id, answer_op_id) VALUES (9,43);
INSERT Into answer(patient_id, answer_op_id) VALUES (9,47);
INSERT Into answer(patient_id, answer_op_id) VALUES (9,51);

INSERT Into answer(patient_id, answer_op_id) VALUES (10,27);
INSERT Into answer(patient_id, answer_op_id) VALUES (10,30);
INSERT Into answer(patient_id, answer_op_id) VALUES (10,36);
INSERT Into answer(patient_id, answer_op_id) VALUES (10,38);
INSERT Into answer(patient_id, answer_op_id) VALUES (10,44);
INSERT Into answer(patient_id, answer_op_id) VALUES (10,48);
INSERT Into answer(patient_id, answer_op_id) VALUES (10,52);