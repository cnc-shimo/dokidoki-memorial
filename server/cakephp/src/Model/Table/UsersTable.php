<?php

namespace App\Model\Table;

use Cake\Database\Expression\IdentifierExpression;
use Cake\ORM\Table;

/**
 * @author shimomo0502
 */
class UsersTable extends Table
{
    /**
     * @param  array $conditions
     * @return array
     */
    public function getUsers(array $conditions = [])
    {
        $users = $this->find()->join([
            'c' => [
                'table' => 'couples',
                'type' => 'LEFT OUTER',
                'conditions' => [
                    'OR' => [
                        'c.man_id' => new IdentifierExpression('Users.id'),
                        'c.woman_id' => new IdentifierExpression('Users.id'),
                    ],
                ],
            ],
        ])->select([
            'id'                 => 'Users.id',
            'name'               => 'Users.name',
            'sex'                => 'Users.sex',
            'birthday'           => 'Users.birthday',
            'constellation'      => 'Users.constellation',
            'blood'              => 'Users.blood',
            'birthplace'         => 'Users.birthplace',
            'residence'          => 'Users.residence',
            'hobby'              => 'Users.hobby',
            'created_at'         => 'Users.created_at',
            'updated_at'         => 'Users.updated_at',
            'couple_id'          => 'c.id',
            'couple_man_id'      => 'c.man_id',
            'couple_woman_id'    => 'c.woman_id',
            'couple_anniversary' => 'c.anniversary',
            'couple_created_at'  => 'c.created_at',
            'couple_updated_at'  => 'c.updated_at',
        ]);

        foreach ($conditions as $key => $value) {
            $users->where([$key => $value]);
        }

        return json_decode(json_encode($users->all()), true);
    }
}
