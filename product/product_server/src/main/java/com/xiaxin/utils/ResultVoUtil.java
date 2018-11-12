package com.xiaxin.utils;

import com.xiaxin.enums.ResultEnums;
import com.xiaxin.vo.ResultVo;

/**
 * Created by xiaxin on 2018/2/19.
 */
public class ResultVoUtil {

    public static ResultVo success(Object object) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        resultVo.setData(object);
        return resultVo;
    }

    public static ResultVo success() {
        return success(null);
    }

    public static ResultVo error(ResultEnums resultEnums) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(resultEnums.getCode());
        resultVo.setMsg(resultEnums.getMessage());

        return resultVo;
    }
}
