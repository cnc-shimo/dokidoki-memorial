<?php

namespace App\Model\Table;

use Cake\Database\Expression\IdentifierExpression;
use Cake\ORM\Table;

/**
 * @author shimomo0502
 */
class FrustrationsTable extends Table
{
    /**
     * @param  array $conditions
     * @return array
     */
    public function getFrustrations(array $conditions = [])
    {
        $frustrations = $this->find()->select([
            'id'            => 'Frustrations.id',
            'user_id'       => 'Frustrations.user_id',
            'couple_id'     => 'Frustrations.couple_id',
            'title'         => 'Frustrations.title',
            'message'       => 'Frustrations.message',
            'value'         => 'Frustrations.value',
            'is_read'       => 'Frustrations.is_read',
            'is_eliminated' => 'Frustrations.is_eliminated',
            'created_at'    => 'Frustrations.created_at',
            'updated_at'    => 'Frustrations.updated_at',
        ]);
        return json_decode(json_encode($frustrations->all()), true);
    }

    /**
     * @param  array $conditions
     * @return boolean
     */
    public function postFrustrations(array $data = [])
    {
        $query = $this->query()->insert([
            'user_id',
            'couple_id',
            'title',
            'message',
            'value',
            'is_read',
            'is_eliminated',
            'created_at',
            'updated_at',
        ]);
        foreach ($data as $d) {
            $d['user_id'] = 1;
            $d['couple_id'] = 1;
            $d['is_read'] = false;
            $d['is_eliminated'] = false;
            $d['created_at'] = '2000/01/01 00:00:00';
            $d['updated_at'] = '2000/01/01 00:00:00';
            $query->values($d);
        }
        $query->execute();
        return true;
    }
}
