package com.moowork.javatar;

import com.google.common.hash.Hashing;

public abstract class Md5AvatarGenerator
    extends HashAvatarGenerator
{
    public Md5AvatarGenerator()
    {
        super( Hashing.md5() );
    }
}
