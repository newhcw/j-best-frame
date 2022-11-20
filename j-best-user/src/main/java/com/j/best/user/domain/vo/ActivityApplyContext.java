package com.j.best.user.domain.vo;

import com.j.best.user.domain.request.ActivityApplyHttpRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityApplyContext {


    ActivityApplyHttpRequest applyHttpRequest;

}
