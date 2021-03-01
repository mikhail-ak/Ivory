package sstu.diploma.ivory.storage.listener;

import lombok.val;
import sstu.diploma.ivory.storage.entity.UserEntity;
import sstu.diploma.ivory.storage.entity.mixin.TimeTrack;
import sstu.diploma.ivory.storage.entity.mixin.TimeTracked;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.ZonedDateTime;

public class TimeTrackListener {

    @PrePersist
    private void beforePersist(TimeTracked tt) {
        val time = ZonedDateTime.now();
        val track = new TimeTrack(time, time);
        tt.setTimeTrack(track);
    }

    @PreUpdate
    private void beforeUpdate(TimeTracked tt) {
        val lastTrack = tt.getTimeTrack();
        lastTrack.setUpdatedOn(ZonedDateTime.now());
        tt.setTimeTrack(lastTrack);
    }
}
