package com.autuan.project.mumu.member.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TabMember {
    private String id;

    private String no;

    private String mobile;

    private String email;

    private String password;

    private String safeQuestion;

    private String safeAnswere;

    private Integer level;

    private Integer vip;

    private LocalDateTime vipExpireTime;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String machineCode;

    private String whiteIp;

    private LocalDateTime createTime;

    private String createBy;

    private LocalDateTime updateTime;

    private String updateBy;

    private Integer delFlag;

    public enum Column {
        id("id", "id", "VARCHAR", false),
        no("no", "no", "VARCHAR", false),
        mobile("mobile", "mobile", "VARCHAR", false),
        email("email", "email", "VARCHAR", false),
        password("password", "password", "VARCHAR", false),
        safeQuestion("safe_question", "safeQuestion", "VARCHAR", false),
        safeAnswere("safe_answere", "safeAnswere", "VARCHAR", false),
        level("level", "level", "INTEGER", false),
        vip("vip", "vip", "INTEGER", false),
        vipExpireTime("vip_expire_time", "vipExpireTime", "TIMESTAMP", false),
        startTime("start_time", "startTime", "TIMESTAMP", false),
        endTime("end_time", "endTime", "TIMESTAMP", false),
        machineCode("machine_code", "machineCode", "VARCHAR", false),
        whiteIp("white_ip", "whiteIp", "VARCHAR", false),
        createTime("create_time", "createTime", "TIMESTAMP", false),
        createBy("create_by", "createBy", "VARCHAR", false),
        updateTime("update_time", "updateTime", "TIMESTAMP", false),
        updateBy("update_by", "updateBy", "VARCHAR", false),
        delFlag("del_flag", "delFlag", "INTEGER", false);

        private static final String BEGINNING_DELIMITER = "\"";

        private static final String ENDING_DELIMITER = "\"";

        private final String column;

        private final boolean isColumnNameDelimited;

        private final String javaProperty;

        private final String jdbcType;

        public String value() {
            return this.column;
        }

        public String getValue() {
            return this.column;
        }

        public String getJavaProperty() {
            return this.javaProperty;
        }

        public String getJdbcType() {
            return this.jdbcType;
        }

        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}