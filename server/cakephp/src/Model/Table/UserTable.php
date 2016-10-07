<?php

namespace App\Model\Table;

use Cake\ORM\Table;

/**
 * @author shimo
 */
class UserTable extends Table
{
    /**
     * 使用するテーブルの設定
     *
     * @param  array $config
     * @return void
     */
    public function initialize(array $config)
    {
        $this->table('users');
        $this->primaryKey('id');
    }
}
