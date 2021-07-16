-- stored procedure di esempio per il json con clob ...ma non provata
PROCEDURE UpdtBckAlPct_1(
      p_user_id_nbr            IN  bdgt_user.user_id_nbr%TYPE,
      p_ref_date               IN  VARCHAR2,
      p_act_cd                 IN  act.act_cd%TYPE,
      p_nodeprd_bckal_tab      IN  clob,
      p_rev_nbr                IN  act.rev_nbr%TYPE,
      p_status                 OUT common.StatusType,
      p_user_err_msg_txt       OUT bdgt_mod_err_msg.user_err_msg_txt%TYPE)
  IS
    wk_jo_all JSON_OBJECT_T;
    wk_jo     JSON_OBJECT_T;
    wk_ja        JSON_ARRAY_T;
    
    wk_tab   nodeprd_bckal_tab_type:= nodeprd_bckal_tab_type();
    wk_item  nodeprd_bckal_rec_type;
      
BEGIN

    wk_jo_all := JSON_OBJECT_T.parse(p_nodeprd_bckal_tab);
  
    wk_ja := wk_jo_all.get_Array('listItem');
    
    FOR i IN 0 .. wk_ja.get_size - 1 LOOP
        wk_jo := JSON_OBJECT_T(wk_ja.get(i));
         
        wk_item := nodeprd_bckal_rec_type(wk_jo.get_string ('node_prd_cd'), wk_jo.get_number ('bck_allocation_pct'), wk_jo.get_string ('bck_allocation_flg'));
        wk_tab.extend();
        wk_tab(wk_tab.count) :=wk_item;

            
    END LOOP;   
    
    
    
   PRODBDGT.ACTIONDN.UPDTBCKALPCT ( P_USER_ID_NBR, P_REF_DATE, P_ACT_CD, wk_tab, P_REV_NBR, P_STATUS, P_USER_ERR_MSG_TXT );
    
EXCEPTION
   WHEN OTHERS THEN
    p_status := SQLCODE;
   Rollback;    
    DBMS_OUTPUT.put_line ('UpdtBckAlPct_1 sqlerr:' ||sqlerrm||' sqlcode:'|| sqlcode);

END UpdtBckAlPct_1;
