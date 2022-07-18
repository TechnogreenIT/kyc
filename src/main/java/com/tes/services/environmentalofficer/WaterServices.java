package com.tes.services.environmentalofficer;

import com.tes.model.RegEffPoll;
import com.tes.model.RegSewPoll;

public interface WaterServices
{

	public RegEffPoll save(RegEffPoll regEffPoll);

	public RegSewPoll save(RegSewPoll regSewPoll);

}
