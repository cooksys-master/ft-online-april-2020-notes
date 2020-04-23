package com.cooksys.ftd.assignments.collections;

import com.cooksys.ftd.assignments.collections.properties.BossProperties;
import com.cooksys.ftd.assignments.collections.properties.MegaCorpProperties;
import com.cooksys.ftd.assignments.collections.properties.GruntProperties;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ GruntProperties.class, BossProperties.class, MegaCorpProperties.class })
public class TestSuite {
}
