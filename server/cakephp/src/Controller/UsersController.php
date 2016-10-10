<?php

/**
 * CakePHP(tm) : Rapid Development Framework (http://cakephp.org)
 * Copyright (c) Cake Software Foundation, Inc. (http://cakefoundation.org)
 *
 * Licensed under The MIT License
 * For full copyright and license information, please see the LICENSE.txt
 * Redistributions of files must retain the above copyright notice.
 *
 * @copyright Copyright (c) Cake Software Foundation, Inc. (http://cakefoundation.org)
 * @link      http://cakephp.org CakePHP(tm) Project
 * @since     0.2.9
 * @license   http://www.opensource.org/licenses/mit-license.php MIT License
 */
namespace App\Controller;

use Cake\ORM\TableRegistry;

/**
 * @author shimomo0502
 */
class UsersController extends AppController
{
    /**
     * @var array
     */
    public $components = ['RequestHandler'];

    /**
     * @return void
     */
    public function index()
    {
        $users = $this->Users->getUsers();
        $this->set([
            'users' => $users,
            '_serialize' => [
                'users',
            ],
        ]);
    }

    /**
     * @return void
     */
    public function view(string $id)
    {
        $user = $this->Users->find()->where([
            'Users.id' => $id,
        ])->join([
            'c' => [
                'table' => 'couples',
                'type' => 'INNER',
                'conditions' => [
                    'OR' => [
                        'c.man_id' => new \Cake\Database\Expression\IdentifierExpression('Users.id'),
                        'c.woman_id' => new \Cake\Database\Expression\IdentifierExpression('Users.id'),
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
        ])->first();

        $this->set([
            'id'                 => $user['id'],
            'name'               => $user['name'],
            'sex'                => $user['sex'],
            'birthday'           => $user['birthday'],
            'constellation'      => $user['constellation'],
            'blood'              => $user['blood'],
            'birthplace'         => $user['birthplace'],
            'residence'          => $user['residence'],
            'hobby'              => $user['hobby'],
            'created_at'         => $user['created_at'],
            'updated_at'         => $user['updated_at'],
            'couple_id'          => $user['couple_id'],
            'couple_man_id'      => $user['couple_man_id'],
            'couple_woman_id'    => $user['couple_woman_id'],
            'couple_anniversary' => $user['anniversary'],
            'couple_created_at'  => $user['couple_created_at'],
            'couple_updated_at'  => $user['couple_updated_at'],
            '_serialize' => [
                'id',
                'name',
                'sex',
                'birthday',
                'constellation',
                'blood',
                'birthplace',
                'residence',
                'hobby',
                'created_at',
                'updated_at',
                'couple_id',
                'couple_man_id',
                'couple_woman_id',
                'couple_anniversary',
                'couple_created_at',
                'couple_updated_at',
            ],
        ]);
    }
}
